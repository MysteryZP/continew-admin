package top.continew.admin.payConfig.controller;

import top.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.admin.payConfig.model.query.PayMethodQuery;
import top.continew.admin.payConfig.model.req.PayMethodReq;
import top.continew.admin.payConfig.model.resp.PayMethodDetailResp;
import top.continew.admin.payConfig.model.resp.PayMethodResp;
import top.continew.admin.payConfig.service.PayMethodService;

/**
 * 支付方式配置管理 API
 *
 * @author Jhon sam
 * @since 2024/08/02 10:46
 */
@Tag(name = "支付方式配置管理 API")
@RestController
@CrudRequestMapping(value = "/payConfig/payMethod", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class PayMethodController extends BaseController<PayMethodService, PayMethodResp, PayMethodDetailResp, PayMethodQuery, PayMethodReq> {}