package top.continew.admin.controller.payOrder;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.channel.IPaymentService;
import top.continew.admin.common.constant.PayConstants;
import top.continew.admin.common.enums.DivisionEnum;
import top.continew.admin.common.enums.OrderStatusEnum;
import top.continew.admin.common.resp.ChannelResp;
import top.continew.admin.common.util.amount.AmountKits;
import top.continew.admin.common.util.lang.StringKits;
import top.continew.admin.common.util.spring.SpringBeansUtil;
import top.continew.admin.exception.ChannelException;
import top.continew.admin.merchantManage.model.entity.ApplicationDO;
import top.continew.admin.merchantManage.model.entity.MerchantDO;
import top.continew.admin.merchantManage.model.entity.MerchantPayMethodDO;
import top.continew.admin.merchantManage.service.MerchantPayMethodService;
import top.continew.admin.model.req.CommonPayDataReq;
import top.continew.admin.model.req.UnifiedOrderReq;
import top.continew.admin.model.resp.ApiResp;
import top.continew.admin.model.resp.CommonPayDataResp;
import top.continew.admin.model.resp.MchAppConfigContextResp;
import top.continew.admin.model.resp.UnifiedOrderResp;
import top.continew.admin.orderManage.model.entity.PayOrderDO;
import top.continew.admin.orderManage.model.req.PayOrderReq;
import top.continew.admin.orderManage.service.PayOrderService;
import top.continew.admin.service.ConfigContextQueryService;
import top.continew.admin.service.PayOrderProcessService;
import top.continew.admin.util.PaymentServiceFactory;
import top.continew.starter.core.util.validate.CheckUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

/**
 * @author Zhang SAM
 * @description 创建支付订单抽象类
 * @since 2024/8/13 10:41
 */
@Tag(name = "统一下单抽象接口")
@Slf4j
@RestController
public class AbstractPayOrderController {

    @Autowired
    private  PayOrderService orderService;
    @Autowired
    private  ConfigContextQueryService configContextQueryService;
    @Autowired
    private  MerchantPayMethodService merchantPayMethodService;
    @Autowired
    private PayOrderProcessService payOrderProcessService;
    @Autowired
    private PaymentServiceFactory paymentServiceFactory;





    /** 统一下单 (新建订单模式) **/
    protected ApiResp unifiedOrder(String payCode,String payMethodCode, UnifiedOrderReq bizRQ){
        return unifiedOrder(payMethodCode, bizRQ, null);
    }

    /** 统一下单 **/
    protected ApiResp unifiedOrder(String payMethodCode, UnifiedOrderReq unifiedOrderReq, PayOrderDO payOrder){

        // 响应数据
        UnifiedOrderResp unifiedOrderResp = null;

        //是否新订单模式 [  一般接口都为新订单模式，  由于QR_CASHIER支付方式，需要先 在DB插入一个新订单， 导致此处需要特殊判断下。 如果已存在则直接更新，否则为插入。  ]
        boolean isNewOrder = payOrder == null;

        try {

            if(payOrder != null){ //当订单存在时，封装公共参数。

                CheckUtils.throwIf(payOrder.getStatus() != OrderStatusEnum.INIT.getValue(),"订单状态异常");

                payOrder.setPayCode(payMethodCode); // 需要将订单更新 支付方式
                //todo:需要补充更新渠道用户信息
//                payOrder.setChannelUser(bizRQ.getChannelUserId()); //更新渠道用户信息
                unifiedOrderReq.setMchNo(payOrder.getMchNo());
                unifiedOrderReq.setAppNo(payOrder.getAppNo());
                unifiedOrderReq.setMchOrderNo(payOrder.getMchOrderNo());
                unifiedOrderReq.setPayCode(unifiedOrderReq.getPayCode());
                unifiedOrderReq.setPayMethodCode(payMethodCode);
                unifiedOrderReq.setAmount(payOrder.getAmount());
                unifiedOrderReq.setCurrency(payOrder.getCurrency());
                unifiedOrderReq.setClientIp(payOrder.getClientIp());
                unifiedOrderReq.setSubject(payOrder.getSubject());
                unifiedOrderReq.setNotifyUrl(payOrder.getNotifyUrl());
                unifiedOrderReq.setReturnUrl(payOrder.getReturnUrl());
                //todo：额外参数设置暂时忽略
//                bizRQ.setChannelExtra(payOrder.getChannelExtra());
//                bizRQ.setExtParam(payOrder.getExtParam());
                unifiedOrderReq.setDivisionMode(payOrder.getDivisionMode());
            }

            String mchNo = unifiedOrderReq.getMchNo();
            String appNo = unifiedOrderReq.getAppNo();

            // 只有新订单模式，进行校验
            CheckUtils.throwIf(
        isNewOrder && orderService.getOrderByMchOrderNoAndMerchantNo(mchNo, unifiedOrderReq.getMchOrderNo()).size() > 0,
        "商户订单["+unifiedOrderReq.getMchOrderNo()+"]已存在"
            );

            CheckUtils.throwIf(
        StringUtils.isNotEmpty(unifiedOrderReq.getNotifyUrl()) && !StringKits.isAvailableUrl(unifiedOrderReq.getNotifyUrl()),
        "异步通知地址协议仅支持http:// 或 https:// !"
            );

            CheckUtils.throwIf(
        StringUtils.isNotEmpty(unifiedOrderReq.getReturnUrl()) && !StringKits.isAvailableUrl(unifiedOrderReq.getReturnUrl()),
        "同步通知地址协议仅支持http:// 或 https:// !"
            );

            //获取支付参数 (缓存数据) 和 商户信息
            MchAppConfigContextResp mchAppConfigContext = configContextQueryService.queryMchInfoAndAppInfo(mchNo, appNo);
            CheckUtils.throwIfNull(mchAppConfigContext,"获取商户应用信息失败");

            MerchantDO mchInfo = mchAppConfigContext.getMchInfo();
            ApplicationDO mchApp = mchAppConfigContext.getMchApp();

            //收银台支付并且只有新订单需要走这里，  收银台二次下单的wayCode应该为实际支付方式。
            if(isNewOrder && PayConstants.PAY_WAY_CODE.QR_CASHIER.equals(payMethodCode)){
                //生成订单
                payOrder = genPayOrder(unifiedOrderReq, mchInfo, mchApp, null, null);
                Long payOrderId = payOrder.getId();
                //订单入库 订单状态： 生成状态  此时没有和任何上游渠道产生交互。
                PayOrderReq orderReq = BeanUtil.copyProperties(payOrder, PayOrderReq.class);
                Long orderId = orderService.add(orderReq);

                CommonPayDataResp qrCashierOrderRS = new CommonPayDataResp();
                CommonPayDataReq qrCashierOrderRQ = (CommonPayDataReq)unifiedOrderReq;


                //todo:生成支付地址进行跳转，此处先随便填写
                String payUrl = "https://www.baidu.com/pay.html";
//                String payUrl = dbApplicationConfig.genUniJsapiPayUrl(payOrderId);
                qrCashierOrderRS.setPayUrl(payUrl);

                return packageApiResByPayOrder(unifiedOrderReq, qrCashierOrderRS, payOrder);
            }

            // 根据支付方式， 查询出 该商户 可用的支付接口
            MerchantPayMethodDO mchPayPassage = merchantPayMethodService.getMerchanPayMethodByPrimaryInfo(mchAppConfigContext.getMchNo(),mchAppConfigContext.getAppNo(), unifiedOrderReq.getPayCode(),payMethodCode);
            CheckUtils.throwIfNull(mchPayPassage,"商户应用不支持该支付方式");

            //获取支付接口
            IPaymentService paymentService = paymentServiceFactory.getPaymentService(unifiedOrderReq.getPayCode(),unifiedOrderReq.getPayMethodCode(),mchPayPassage);
            String ifCode = paymentService.getPayCode();

            //生成订单
            if(isNewOrder){
                payOrder = genPayOrder(unifiedOrderReq, mchInfo, mchApp, ifCode, mchPayPassage);
            }else{
                payOrder.setPayCode(ifCode);

                // 查询支付方式的费率，并 在更新ing时更新费率信息
                payOrder.setMchFeeRate(mchPayPassage.getFee());
                payOrder.setMchFeeAmount(AmountKits.calPercentageFee(payOrder.getAmount(), payOrder.getMchFeeRate())); //商户手续费,单位分
            }

            //预先校验
            String errMsg = paymentService.preCheck(unifiedOrderReq, payOrder);
            CheckUtils.throwIfNotEmpty(errMsg,errMsg);

            if(isNewOrder){
                 payOrder.setPayOrderNo(StringKits.generateOrderNumber());
                //订单入库 订单状态： 生成状态  此时没有和任何上游渠道产生交互。
                PayOrderReq orderReq = BeanUtil.copyProperties(payOrder,PayOrderReq.class);
                orderReq.setCreateUser(1l);//默认系统
                orderReq.setCreateTime(LocalDateTime.now());//默认系统
                orderService.add(orderReq);
            }

            //调起上游支付接口
            unifiedOrderResp = (UnifiedOrderResp) paymentService.pay(unifiedOrderReq, payOrder, mchAppConfigContext);

            //处理上游返回数据
            this.processChannelMsg(unifiedOrderResp.getChannelRetMsg(), payOrder);
            return packageApiResByPayOrder(unifiedOrderReq, unifiedOrderResp, payOrder);

        } catch (ChannelException e) {
            return handleChannelException(e, unifiedOrderReq, unifiedOrderResp, payOrder);
        }
    }

    private PayOrderDO genPayOrder(UnifiedOrderReq req, MerchantDO mchInfo, ApplicationDO mchApp, String payCode, MerchantPayMethodDO mchPayPassage){
        PayOrderDO payOrder = BeanUtil.copyProperties(req,PayOrderDO.class);
        payOrder.setMchOrderNo(mchInfo.getMchNo());//商户号
        //todo: 1.选择服务商，然后提供服务商号填写至订单信息当中
        //      2.payOrder.setIsvNo(mchInfo.getIsvNo()); //服务商号

        payOrder.setMchName(mchInfo.getMchShortName()); //商户名称（简称）
        payOrder.setMchType(mchInfo.getType()); //商户类型
        payOrder.setAppNo(mchApp.getAppNo()); //商户应用appId
        payOrder.setPayCode(payCode); //接口代码

        if(mchPayPassage != null){
            payOrder.setMchFeeRate(mchPayPassage.getFee()); //商户手续费费率快照
        }else{
            payOrder.setMchFeeRate(BigDecimal.ZERO); //预下单模式， 按照0计算入库， 后续进行更新
        }

        payOrder.setMchFeeAmount(AmountKits.calPercentageFee(payOrder.getAmount(), mchPayPassage.getFee().divide(new BigDecimal("100")))); //商户手续费,单位分

        payOrder.setStatus(OrderStatusEnum.INIT.getValue()); //订单状态, 默认订单生成状态
        payOrder.setClientIp(StringUtils.defaultIfEmpty(req.getClientIp(), null)); //客户端IP

        // 分账模式
        payOrder.setDivisionMode(ObjectUtils.defaultIfNull(req.getDivisionMode(), DivisionEnum.DIVISION_MODE_FORBID.getValue()));

        LocalDateTime nowDate = LocalDateTime.now();

        //订单过期时间 单位： 秒
        if(req.getExpiredTime() != null){
            payOrder.setExpiredTime(nowDate.plusSeconds(req.getExpiredTime()));
        }else{
            payOrder.setExpiredTime(nowDate.plusHours(2)); // 订单过期时间 默认两个小时
        }
        return payOrder;
    }


    /**
     * todo:暂时使用数据库查询，后续需要把商户信息缓存在内存当中避免多次查询调用
     * 参考jeepay的 MchAppConfigContext 类
     * 校验： 商户的支付方式是否可用
     * 返回： 支付接口
     * **/
    private IPaymentService checkMchWayCodeAndGetService(MchAppConfigContextResp mchAppConfigContext, MerchantPayMethodDO mchPayPassage){

        // 接口代码
        String payCode = mchPayPassage.getPayCode();
        IPaymentService paymentService = SpringBeansUtil.getBean(payCode + "PaymentService", IPaymentService.class);
        CheckUtils.throwIfNull(paymentService, "无此支付通道接口");
        CheckUtils.throwIf(!paymentService.isSupport(mchPayPassage.getPayMethodCode()),"接口不支持该支付方式");

        //todo:待补充商户应用参数配置逻辑，jeepay同名方法

        return paymentService;
    }


    /** 处理返回的渠道信息，并更新订单状态
     *  payOrder将对部分信息进行 赋值操作。
     * **/
    private void processChannelMsg(ChannelResp channelRetMsg, PayOrderDO payOrder){

        //对象为空 || 上游返回状态为空， 则无需操作
        if(channelRetMsg == null || channelRetMsg.getChannelState() == null){
            return ;
        }

        //明确成功
        if(ChannelResp.ChannelState.CONFIRM_SUCCESS == channelRetMsg.getChannelState()) {

            this.updateInitOrderStateThrowException(OrderStatusEnum.SUCCESS.getValue(), payOrder, channelRetMsg);

            //订单支付成功，其他业务逻辑
            payOrderProcessService.confirmSuccess(payOrder);

            //明确失败
        }else if(ChannelResp.ChannelState.CONFIRM_FAIL == channelRetMsg.getChannelState()) {

            this.updateInitOrderStateThrowException(OrderStatusEnum.FAIL.getValue(), payOrder, channelRetMsg);

            // 上游处理中 || 未知 || 上游接口返回异常  订单为支付中状态
        }else if( ChannelResp.ChannelState.WAITING == channelRetMsg.getChannelState() ||
                ChannelResp.ChannelState.UNKNOWN == channelRetMsg.getChannelState() ||
                ChannelResp.ChannelState.API_RET_ERROR == channelRetMsg.getChannelState()

        ){
            this.updateInitOrderStateThrowException(OrderStatusEnum.ING.getValue(),  payOrder, channelRetMsg);

            // 系统异常：  订单不再处理。  为： 生成状态
        }else if( ChannelResp.ChannelState.SYS_ERROR == channelRetMsg.getChannelState()){

        }else{
            CheckUtils.throwIf(true,"ChannelState 返回异常！");
        }

        //todo:判断是否需要轮询查单
        //判断是否需要轮询查单
//        if(channelRetMsg.isNeedQuery()){
//            mqSender.send(PayOrderReissueMQ.build(payOrderId, 1), 5);
//        }

    }


    /** 更新订单状态 --》 订单生成--》 其他状态  (向外抛出异常) **/
    private void updateInitOrderStateThrowException(Integer orderState, PayOrderDO payOrder, ChannelResp channelRetMsg){

        payOrder.setStatus(orderState);
        //todo:后期需要补充渠道错误信息和错误码，参考jeepay同类同方法

        boolean isSuccess = orderService.updateInit2Ing(payOrder.getId(), payOrder);

        CheckUtils.throwIf(!isSuccess,"更新订单异常");

        isSuccess = orderService.updateIng2SuccessOrFail(payOrder.getId(), payOrder.getStatus(),
                channelRetMsg.getChannelOrderId(), channelRetMsg.getChannelUserId(), channelRetMsg.getChannelErrCode(), channelRetMsg.getChannelErrMsg());

        CheckUtils.throwIf(!isSuccess,"更新订单异常");
    }


    /** 统一封装订单数据  **/
    private ApiResp packageApiResByPayOrder(UnifiedOrderReq unifiedOrderReq, UnifiedOrderResp unifiedOrderResp, PayOrderDO payOrder){

        // 返回接口数据
        unifiedOrderResp.setPayOrderId(payOrder.getPayOrderNo());
        unifiedOrderResp.setOrderState(payOrder.getStatus());
        unifiedOrderResp.setMchOrderNo(payOrder.getMchOrderNo());

        if(payOrder.getStatus() == OrderStatusEnum.FAIL.getValue()){
            unifiedOrderResp.setErrCode(unifiedOrderResp.getChannelRetMsg() != null ? unifiedOrderResp.getChannelRetMsg().getChannelErrCode() : null);
            unifiedOrderResp.setErrMsg(unifiedOrderResp.getChannelRetMsg() != null ? unifiedOrderResp.getChannelRetMsg().getChannelErrMsg() : null);
        }

        return ApiResp.okWithSign(unifiedOrderResp, configContextQueryService.queryMchInfoAndAppInfo(unifiedOrderReq.getMchNo(), unifiedOrderReq.getAppNo()).getAppSecret());
    }


    private ApiResp handleChannelException(ChannelException e, UnifiedOrderReq unifiedOrderReq, UnifiedOrderResp unifiedOrderResp, PayOrderDO payOrder) {
        this.processChannelMsg(e.getChannelResp(), payOrder);
        if (e.getChannelResp().getChannelState() == ChannelResp.ChannelState.SYS_ERROR) {
            return ApiResp.customFail(e.getMessage());
        }
        return packageApiResByPayOrder(unifiedOrderReq, unifiedOrderResp, payOrder);
    }

}
