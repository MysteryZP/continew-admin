package top.continew.admin.merchantManage.service;

import top.continew.admin.merchantManage.model.entity.ApplicationDO;
import top.continew.starter.extension.crud.service.BaseService;
import top.continew.admin.merchantManage.model.query.ApplicationQuery;
import top.continew.admin.merchantManage.model.req.ApplicationReq;
import top.continew.admin.merchantManage.model.resp.ApplicationDetailResp;
import top.continew.admin.merchantManage.model.resp.ApplicationResp;

/**
 * 应用管理业务接口
 *
 * @author Jhon sam
 * @since 2024/08/01 15:36
 */
public interface ApplicationService extends BaseService<ApplicationResp, ApplicationDetailResp, ApplicationQuery, ApplicationReq> {
    ApplicationDO getApplicationByAppNo(String appNo);
}