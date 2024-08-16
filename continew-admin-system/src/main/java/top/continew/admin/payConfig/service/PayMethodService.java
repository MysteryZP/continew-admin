package top.continew.admin.payConfig.service;

import top.continew.starter.extension.crud.service.BaseService;
import top.continew.admin.payConfig.model.query.PayMethodQuery;
import top.continew.admin.payConfig.model.req.PayMethodReq;
import top.continew.admin.payConfig.model.resp.PayMethodDetailResp;
import top.continew.admin.payConfig.model.resp.PayMethodResp;

/**
 * 支付方式配置业务接口
 *
 * @author Jhon sam
 * @since 2024/08/02 10:46
 */
public interface PayMethodService extends BaseService<PayMethodResp, PayMethodDetailResp, PayMethodQuery, PayMethodReq> {}