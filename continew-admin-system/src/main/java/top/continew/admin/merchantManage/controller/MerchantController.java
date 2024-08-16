package top.continew.admin.merchantManage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import top.continew.admin.common.util.helper.LoginHelper;
import top.continew.admin.system.model.query.RoleQuery;
import top.continew.admin.system.model.resp.MessageUnreadResp;
import top.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.controller.BaseController;
import top.continew.admin.merchantManage.model.query.MerchantQuery;
import top.continew.admin.merchantManage.model.req.MerchantReq;
import top.continew.admin.merchantManage.model.resp.MerchantDetailResp;
import top.continew.admin.merchantManage.model.resp.MerchantResp;
import top.continew.admin.merchantManage.service.MerchantService;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.model.resp.LabelValueResp;
import top.continew.starter.log.core.annotation.Log;
import top.continew.starter.web.model.R;

import java.util.List;
import java.util.Optional;

/**
 * 商户管理管理 API
 *
 * @author Jhon sam
 * @since 2024/07/30 16:14
 */
@Tag(name = "商户管理管理 API")
@RestController
@RequiredArgsConstructor
@CrudRequestMapping(value = "/merchantManage/merchant", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class MerchantController extends BaseController<MerchantService, MerchantResp, MerchantDetailResp, MerchantQuery, MerchantReq> {

    private final MerchantService merchantService;

    @Operation(summary = "查询所有商户列表", description = "查询所有商户列表供下拉框使用")
    @Parameter(name = "isDetail", description = "是否查询详情", example = "true", in = ParameterIn.QUERY)
    @GetMapping("/dict/merchant")
    public R<List<LabelValueResp>> listMerchantDict(MerchantQuery query, SortQuery sortQuery) {
        List<LabelValueResp> result = merchantService.listDict(query, sortQuery);
        return R.ok(result);
    }
}