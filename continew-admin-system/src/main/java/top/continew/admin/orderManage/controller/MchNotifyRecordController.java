package top.continew.admin.orderManage.controller;

import top.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.admin.orderManage.model.query.MchNotifyRecordQuery;
import top.continew.admin.orderManage.model.req.MchNotifyRecordReq;
import top.continew.admin.orderManage.model.resp.MchNotifyRecordDetailResp;
import top.continew.admin.orderManage.model.resp.MchNotifyRecordResp;
import top.continew.admin.orderManage.service.MchNotifyRecordService;

/**
 * 商户通知管理 API
 *
 * @author Jhon sam
 * @since 2024/08/15 11:19
 */
@Tag(name = "商户通知管理 API")
@RestController
@CrudRequestMapping(value = "/orderManage/mchNotifyRecord", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class MchNotifyRecordController extends BaseController<MchNotifyRecordService, MchNotifyRecordResp, MchNotifyRecordDetailResp, MchNotifyRecordQuery, MchNotifyRecordReq> {}