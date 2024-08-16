package top.continew.admin.merchantManage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import top.continew.admin.common.util.helper.LoginHelper;
import top.continew.admin.system.model.query.MessageQuery;
import top.continew.admin.system.model.resp.MessageResp;
import top.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.admin.merchantManage.model.query.MerchantPayMethodQuery;
import top.continew.admin.merchantManage.model.req.MerchantPayMethodReq;
import top.continew.admin.merchantManage.model.resp.MerchantPayMethodDetailResp;
import top.continew.admin.merchantManage.model.resp.MerchantPayMethodResp;
import top.continew.admin.merchantManage.service.MerchantPayMethodService;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.extension.crud.model.resp.PageResp;
import top.continew.starter.web.model.R;

import java.util.List;

/**
 * 商户支付方式配置管理 API
 *
 * @author Jhon sam
 * @since 2024/08/06 17:39
 */
@Tag(name = "商户支付方式配置管理 API")
@RestController
@RequiredArgsConstructor
@CrudRequestMapping(value = "/merchantManage/merchantPayMethod", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class MerchantPayMethodController extends BaseController<MerchantPayMethodService, MerchantPayMethodResp, MerchantPayMethodDetailResp, MerchantPayMethodQuery, MerchantPayMethodReq> {

    private final MerchantPayMethodService merchantPayMethodService;

    @Operation(summary = "查询商户支付配置方式", description = "查询商户支付配置方式")
    @PostMapping("/getMarchantPayMethod")
    public R<MerchantPayMethodDetailResp> getMarchantPayMethod(@Validated @RequestBody MerchantPayMethodQuery query) {
        return R.ok(merchantPayMethodService.getMerchantInfo(query));
    }
}