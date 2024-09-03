package top.continew.admin.orderManage.service;

import top.continew.admin.orderManage.model.entity.PayOrderDO;
import top.continew.starter.extension.crud.service.BaseService;
import top.continew.admin.orderManage.model.query.PayOrderQuery;
import top.continew.admin.orderManage.model.req.PayOrderReq;
import top.continew.admin.orderManage.model.resp.PayOrderDetailResp;
import top.continew.admin.orderManage.model.resp.PayOrderResp;

import java.util.List;

/**
 * 支付订单业务接口
 *
 * @author Jhon sam
 * @since 2024/08/15 11:08
 */
public interface PayOrderService extends BaseService<PayOrderResp, PayOrderDetailResp, PayOrderQuery, PayOrderReq> {

    /**
     * 根据商户号和商户订单号
     *
     * @param merchantNo 商户号
     * @param merchantOrderNo 商户订单号
     * @return 字典项列表
     */
    List<PayOrderDO> getOrderByMchOrderNoAndMerchantNo(String merchantNo, String merchantOrderNo);

    boolean updateInit2Ing(String id, PayOrderDO payOrder);

    public boolean updateIng2Success(String id, String channelOrderNo, String channelUserId);

    public boolean updateIng2Fail(String id, String channelOrderNo, String channelUserId, String channelErrCode, String channelErrMsg);

    boolean updateIng2SuccessOrFail(String id, Integer status, String channelOrderId, String channelUserId, String channelErrCode, String channelErrMsg);

    PayOrderDO getByOrderNo(String payOrderNo);
}