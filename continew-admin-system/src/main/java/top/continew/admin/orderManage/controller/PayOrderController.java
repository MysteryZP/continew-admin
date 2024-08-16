package top.continew.admin.orderManage.controller;

import top.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.admin.orderManage.model.query.PayOrderQuery;
import top.continew.admin.orderManage.model.req.PayOrderReq;
import top.continew.admin.orderManage.model.resp.PayOrderDetailResp;
import top.continew.admin.orderManage.model.resp.PayOrderResp;
import top.continew.admin.orderManage.service.PayOrderService;

/**
 * 支付订单管理 API
 *
 * @author Jhon sam
 * @since 2024/08/15 11:08
 */
@Tag(name = "支付订单管理 API")
@RestController
@CrudRequestMapping(value = "/orderManage/payOrder", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class PayOrderController extends BaseController<PayOrderService, PayOrderResp, PayOrderDetailResp, PayOrderQuery, PayOrderReq> {}