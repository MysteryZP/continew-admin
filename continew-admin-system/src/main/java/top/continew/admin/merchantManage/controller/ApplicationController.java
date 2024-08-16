package top.continew.admin.merchantManage.controller;

import top.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.admin.merchantManage.model.query.ApplicationQuery;
import top.continew.admin.merchantManage.model.req.ApplicationReq;
import top.continew.admin.merchantManage.model.resp.ApplicationDetailResp;
import top.continew.admin.merchantManage.model.resp.ApplicationResp;
import top.continew.admin.merchantManage.service.ApplicationService;

/**
 * 应用管理管理 API
 *
 * @author Jhon sam
 * @since 2024/08/01 15:36
 */
@Tag(name = "应用管理管理 API")
@RestController
@CrudRequestMapping(value = "/merchantManage/application", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class ApplicationController extends BaseController<ApplicationService, ApplicationResp, ApplicationDetailResp, ApplicationQuery, ApplicationReq> {}