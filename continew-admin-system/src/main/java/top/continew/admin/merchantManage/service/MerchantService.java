package top.continew.admin.merchantManage.service;

import top.continew.admin.merchantManage.model.entity.MerchantDO;
import top.continew.starter.extension.crud.service.BaseService;
import top.continew.admin.merchantManage.model.query.MerchantQuery;
import top.continew.admin.merchantManage.model.req.MerchantReq;
import top.continew.admin.merchantManage.model.resp.MerchantDetailResp;
import top.continew.admin.merchantManage.model.resp.MerchantResp;

import java.util.List;

/**
 * 商户管理业务接口
 *
 * @author Jhon sam
 * @since 2024/07/30 16:14
 */
public interface MerchantService extends BaseService<MerchantResp, MerchantDetailResp, MerchantQuery, MerchantReq> {

    MerchantDO getMerchantByMchNo(String mchNo);
}