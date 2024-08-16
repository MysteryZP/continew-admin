package top.continew.admin.merchantManage.service;

import top.continew.admin.merchantManage.model.entity.MerchantPayMethodDO;
import top.continew.starter.extension.crud.service.BaseService;
import top.continew.admin.merchantManage.model.query.MerchantPayMethodQuery;
import top.continew.admin.merchantManage.model.req.MerchantPayMethodReq;
import top.continew.admin.merchantManage.model.resp.MerchantPayMethodDetailResp;
import top.continew.admin.merchantManage.model.resp.MerchantPayMethodResp;

/**
 * 商户支付方式配置业务接口
 *
 * @author Jhon sam
 * @since 2024/08/06 17:39
 */
public interface MerchantPayMethodService extends BaseService<MerchantPayMethodResp, MerchantPayMethodDetailResp, MerchantPayMethodQuery, MerchantPayMethodReq> {

    MerchantPayMethodDetailResp getMerchantInfo(MerchantPayMethodQuery query);

    MerchantPayMethodDO getMerchanPayMethodByPrimaryInfo(String mchNo, String appNo, String payCode, String payMethodCode);
}