package top.continew.admin.orderManage.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.continew.admin.common.enums.OrderStatusEnum;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.admin.orderManage.mapper.PayOrderMapper;
import top.continew.admin.orderManage.model.entity.PayOrderDO;
import top.continew.admin.orderManage.model.query.PayOrderQuery;
import top.continew.admin.orderManage.model.req.PayOrderReq;
import top.continew.admin.orderManage.model.resp.PayOrderDetailResp;
import top.continew.admin.orderManage.model.resp.PayOrderResp;
import top.continew.admin.orderManage.service.PayOrderService;

import java.util.List;

/**
 * 支付订单业务实现
 *
 * @author Jhon sam
 * @since 2024/08/15 11:08
 */
@Service
@RequiredArgsConstructor
public class PayOrderServiceImpl extends BaseServiceImpl<PayOrderMapper, PayOrderDO, PayOrderResp, PayOrderDetailResp, PayOrderQuery, PayOrderReq> implements PayOrderService {

    @Override
    public List<PayOrderDO> getOrderByMchOrderNoAndMerchantNo(String merchantNo, String merchantOrderNo) {
        return baseMapper.getOrdersByMerchantNoAndMerchantOrderNo(merchantOrderNo,merchantNo);
    }

    @Override
    public boolean updateInit2Ing(Long id, PayOrderDO payOrder) {

        PayOrderDO updateRecord = new PayOrderDO();
        updateRecord.setStatus(OrderStatusEnum.ING.getValue());

        //同时更新， 未确定 --》 已确定的其他信息。  如支付接口的确认、 费率的计算。
        updateRecord.setPayCode(payOrder.getPayCode());
        updateRecord.setPayMethodCode(payOrder.getPayMethodCode());
        updateRecord.setMchFeeRate(payOrder.getMchFeeRate());
        updateRecord.setMchFeeAmount(payOrder.getMchFeeAmount());
        return baseMapper.update(updateRecord, new LambdaUpdateWrapper<PayOrderDO>()
                .eq(PayOrderDO::getId, id).eq(PayOrderDO::getStatus,OrderStatusEnum.INIT.getValue())) > 0;
    }

    @Override
    public boolean updateIng2Success(Long id, String channelOrderNo, String channelUserId) {
        PayOrderDO updateRecord = new PayOrderDO();
        updateRecord.setStatus(OrderStatusEnum.SUCCESS.getValue());
        //todo:需要补充支付成功时间字段
//        updateRecord.setSuccessTime(new Date());

        return baseMapper.update(updateRecord, new LambdaUpdateWrapper<PayOrderDO>()
                .eq(PayOrderDO::getId, id).eq(PayOrderDO::getStatus, OrderStatusEnum.ING.getValue()))>0;
    }

    @Override
    public boolean updateIng2Fail(Long id, String channelOrderNo, String channelUserId, String channelErrCode, String channelErrMsg) {
        PayOrderDO updateRecord = new PayOrderDO();
        updateRecord.setStatus(OrderStatusEnum.FAIL.getValue());

        //todo:需要补充支付失败信息，代码和渠道信息

        return update(updateRecord, new LambdaUpdateWrapper<PayOrderDO>()
                .eq(PayOrderDO::getId, id).eq(PayOrderDO::getStatus, OrderStatusEnum.ING.getValue()));
    }

    @Override
    public boolean updateIng2SuccessOrFail(Long id, Integer status, String channelOrderId, String channelUserId, String channelErrCode, String channelErrMsg) {
        if(status == OrderStatusEnum.ING.getValue()){
            return true;
        }else if(status == OrderStatusEnum.SUCCESS.getValue()){
            return updateIng2Success(id, channelOrderId, channelUserId);
        }else if(status == OrderStatusEnum.FAIL.getValue()){
            return updateIng2Fail(id, channelOrderId, channelUserId, channelErrCode, channelErrMsg);
        }
        return false;
    }
}