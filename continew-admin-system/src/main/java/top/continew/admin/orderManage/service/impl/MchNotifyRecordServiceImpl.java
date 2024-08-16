package top.continew.admin.orderManage.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.admin.orderManage.mapper.MchNotifyRecordMapper;
import top.continew.admin.orderManage.model.entity.MchNotifyRecordDO;
import top.continew.admin.orderManage.model.query.MchNotifyRecordQuery;
import top.continew.admin.orderManage.model.req.MchNotifyRecordReq;
import top.continew.admin.orderManage.model.resp.MchNotifyRecordDetailResp;
import top.continew.admin.orderManage.model.resp.MchNotifyRecordResp;
import top.continew.admin.orderManage.service.MchNotifyRecordService;

/**
 * 商户通知业务实现
 *
 * @author Jhon sam
 * @since 2024/08/15 11:19
 */
@Service
@RequiredArgsConstructor
public class MchNotifyRecordServiceImpl extends BaseServiceImpl<MchNotifyRecordMapper, MchNotifyRecordDO, MchNotifyRecordResp, MchNotifyRecordDetailResp, MchNotifyRecordQuery, MchNotifyRecordReq> implements MchNotifyRecordService {}