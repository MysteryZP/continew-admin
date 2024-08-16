package top.continew.admin.controller.payOrder;

import cn.dev33.satoken.annotation.SaIgnore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.common.enums.OrderStatusEnum;
import top.continew.admin.model.req.UnifiedOrderReq;
import top.continew.admin.model.resp.ApiResp;
import top.continew.admin.model.resp.UnifiedOrderResp;
import top.continew.starter.web.model.R;

/**
 * @author Peng Zhang
 * @description 统一下单
 * @since 2024/8/12 17:33
 */
@Tag(name = "统一下单接口")
@SaIgnore
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pay")
public class UnifiedOrderController extends AbstractPayOrderController {

    /**
     * 统一下单接口
     * **/
    @Operation(summary = "统一下单接口", description = "统一下单")
    @PostMapping("/unifiedOrder")
    public R<Object> unifiedOrder(@Validated  @RequestBody UnifiedOrderReq req){

        //获取参数 & 验签

        //实现子类的res
        ApiResp apiResp = unifiedOrder(req.getPayCode(),req.getPayMethodCode(), req);
        if(apiResp.getData() == null){
            return R.fail(apiResp);
        }

        UnifiedOrderResp bizRes = (UnifiedOrderResp)apiResp.getData();

        //聚合接口，返回的参数
        UnifiedOrderResp res = new UnifiedOrderResp();
        BeanUtils.copyProperties(bizRes, res);

        //只有 订单生成（QR_CASHIER） || 支付中 || 支付成功返回该数据
        if(bizRes.getOrderState() != null
                && (bizRes.getOrderState() == OrderStatusEnum.INIT.getValue()
                || bizRes.getOrderState() == OrderStatusEnum.ING.getValue()
                || bizRes.getOrderState() == OrderStatusEnum.SUCCESS.getValue()) ){
            res.setPayDataType(bizRes.buildPayDataType());
            res.setPayData(bizRes.buildPayData());
        }
        return R.ok(res);
//        return ApiRes.okWithSign(res, configContextQueryService.queryMchApp(rq.getMchNo(), rq.getAppId()).getAppSecret());
    }
}
