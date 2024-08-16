package top.continew.admin.orderManage.service;

import top.continew.starter.extension.crud.service.BaseService;
import top.continew.admin.orderManage.model.query.MchNotifyRecordQuery;
import top.continew.admin.orderManage.model.req.MchNotifyRecordReq;
import top.continew.admin.orderManage.model.resp.MchNotifyRecordDetailResp;
import top.continew.admin.orderManage.model.resp.MchNotifyRecordResp;

/**
 * 商户通知业务接口
 *
 * @author Jhon sam
 * @since 2024/08/15 11:19
 */
public interface MchNotifyRecordService extends BaseService<MchNotifyRecordResp, MchNotifyRecordDetailResp, MchNotifyRecordQuery, MchNotifyRecordReq> {}