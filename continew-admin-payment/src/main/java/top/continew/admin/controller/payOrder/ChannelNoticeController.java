/*
 * Copyright (c) 2021-2031, 河北计全科技有限公司 (https://www.jeequan.com & jeequan@126.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package top.continew.admin.controller.payOrder;

import cn.dev33.satoken.annotation.SaIgnore;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.continew.admin.channel.IChannelNoticeService;
import top.continew.admin.common.constant.CacheConstants;
import top.continew.admin.common.enums.OrderStatusEnum;
import top.continew.admin.common.util.redis.RedisKits;
import top.continew.admin.merchantManage.service.PayMchNotifyService;
import top.continew.admin.model.msg.ChannelRetMsg;
import top.continew.admin.model.resp.MchAppConfigContextResp;
import top.continew.admin.orderManage.model.entity.PayOrderDO;
import top.continew.admin.orderManage.service.PayOrderService;
import top.continew.admin.service.ConfigContextQueryService;
import top.continew.admin.service.PayOrderProcessService;
import top.continew.admin.util.ChannelNoticeServiceFactory;
import top.continew.starter.core.util.validate.CheckUtils;


/*
* 渠道侧的通知入口Controller 【分为同步跳转（doReturn）和异步回调(doNotify) 】
*
* @author terrfly
* @site https://www.jeequan.com
* @date 2021/6/8 17:26
*/
@Slf4j
@SaIgnore
@Controller
public class ChannelNoticeController {

    @Autowired private PayOrderService payOrderService;
    @Autowired private ConfigContextQueryService configContextQueryService;
    @Autowired private PayMchNotifyService payMchNotifyService;
    @Autowired private PayOrderProcessService payOrderProcessService;

//    @Autowired private MchBalanceService mchBalanceService;

    @Autowired
    private ChannelNoticeServiceFactory channelNoticeServiceFactory;

    /** 异步回调入口 **/
    @ResponseBody
    @RequestMapping(value= { "/api/pay/notify/{payCode}/{payOrderNo}"})
    public ResponseEntity doNotify(HttpServletRequest request, @PathVariable("payCode") String payCode,
                                   @PathVariable(value = "payOrderNo", required = false) String urlPayOrderNo
                                   ){
        //@RequestBody(required = false) JSONObject bodyData

        String payOrderNo = null;
        String logPrefix = "进入[" +payCode+ "]支付回调：urlOrderId：["+ StringUtils.defaultIfEmpty(urlPayOrderNo, "") + "] ";
        log.info("===== {} =====" , logPrefix);

        try {

            // 参数有误
            if(StringUtils.isEmpty(payCode)){
                return ResponseEntity.badRequest().body("ifCode is empty");
            }

            //查询支付接口是否存在
            IChannelNoticeService payNotifyService = channelNoticeServiceFactory.getChannelNoticeService(payCode);

            // 支付通道接口实现不存在
            if(payNotifyService == null){
                log.error("{}, interface not exists ", logPrefix);
                return ResponseEntity.badRequest().body("[" + payCode + "] interface not exists");
            }

            // 解析订单号 和 请求参数
            MutablePair<String, Object> mutablePair = payNotifyService.parseParams(request, urlPayOrderNo, IChannelNoticeService.NoticeTypeEnum.DO_NOTIFY);
            CheckUtils.throwIfNull(mutablePair,"解析数据异常！");

            //解析到订单号
            payOrderNo = mutablePair.left;
            log.info("{}, 解析数据为：payOrderId:{}, params:{}", logPrefix, payOrderNo, mutablePair.getRight());
            CheckUtils.throwIf(StringUtils.isNotEmpty(urlPayOrderNo) && !urlPayOrderNo.equals(payOrderNo),"订单号不匹配！");

            //获取订单号 和 订单数据
            PayOrderDO payOrder = payOrderService.getByOrderNo(payOrderNo);

            //todo:对数据添加缓存锁进行安全处理。
            String payOrderProcessLock = CacheConstants.getCacheKeyOrderProcessLock(payOrder.getPayOrderNo());
            boolean orderProcessLock = RedisKits.acquireLock(payOrderProcessLock,"", 5,CacheConstants.DEFAULT_BALANCE_LOCK_MAX_RETRIES,CacheConstants.DEFAULT_BALANCE_LOCK_INTERVAL);
            if (!orderProcessLock){
                log.error("{}, 订单正在处理中，请稍后再试. payOrderId={} ", logPrefix, payOrderNo);
                return payNotifyService.doNotifyOrderAlreadyProcessing(request);
            }

            // 订单不存在
            if(payOrder == null){
                log.error("{}, 订单不存在. payOrderId={} ", logPrefix, payOrderNo);
                return payNotifyService.doNotifyOrderNotExists(request);
            } else if (payOrder.getStatus() == 2) {
                log.error("{}, 订单已回调，请勿重复回调. payOrderId={} ", logPrefix, payOrderNo);
                return payNotifyService.doNotifyOrderAlreadyCallBack(request);
            }

            //查询出商户应用的配置信息
            MchAppConfigContextResp mchAppConfigContext = configContextQueryService.queryMchInfoAndAppInfo(payOrder.getMchNo(), payOrder.getPayOrderNo());


            //调起接口的回调判断
            ChannelRetMsg notifyResult = payNotifyService.doNotice(request, mutablePair.getRight(), payOrder, mchAppConfigContext, IChannelNoticeService.NoticeTypeEnum.DO_NOTIFY);

            CheckUtils.throwIf(
                    notifyResult == null || notifyResult.getChannelState() == null || notifyResult.getResponseEntity() == null,
                    "处理回调事件异常！");


            boolean updateOrderSuccess = false; //默认失败
            //todo:此处默认跳过支付中状态，参数回调后直接由INIT->SUCCESS/FAILD 状态 后续补充支付中状态
            boolean isSuccess = payOrderService.updateInit2Ing(payOrderNo,payOrder);
            if (isSuccess) payOrder.setStatus(OrderStatusEnum.ING.getValue());
            // 订单是 【支付中状态】
            if(payOrder.getStatus() == OrderStatusEnum.ING.getValue()) {

                //明确成功
                if(ChannelRetMsg.ChannelState.CONFIRM_SUCCESS == notifyResult.getChannelState()) {

                    updateOrderSuccess = payOrderService.updateIng2Success(payOrderNo, notifyResult.getChannelOrderId(), notifyResult.getChannelUserId());

                    //明确失败
                }else if(ChannelRetMsg.ChannelState.CONFIRM_FAIL == notifyResult.getChannelState()) {

                    updateOrderSuccess = payOrderService.updateIng2Fail(payOrderNo, notifyResult.getChannelOrderId(), notifyResult.getChannelUserId(), notifyResult.getChannelErrCode(), notifyResult.getChannelErrMsg());
                }
            }

            // 更新订单 异常
            if(!updateOrderSuccess){
                log.error("{}, updateOrderSuccess = {} ",logPrefix, updateOrderSuccess);
                return payNotifyService.doNotifyOrderStateUpdateFail(request);
            }

            //todo:更新商户钱包信息
            //订单支付成功 其他业务逻辑
            if(notifyResult.getChannelState() == ChannelRetMsg.ChannelState.CONFIRM_SUCCESS){
                //更新商户余额信息
//                mchBalanceService.updateBalance(CountryCodeEnum.BRAZIL.getCountryCode(), payOrder);
                payOrderProcessService.confirmSuccess(payOrder);
            }

            log.info("===== {}, 订单通知完成。 payOrderId={}, parseState = {} =====", logPrefix, payOrderNo, notifyResult.getChannelState());

            return notifyResult.getResponseEntity();

        } catch (Exception e) {
            log.error("{}, payOrderId={}, 系统异常", logPrefix, payOrderNo, e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    /*  跳转到支付成功页面 **/
    private String toReturnPage(String errInfo){


        return "cashier/returnPage";
    }

}
