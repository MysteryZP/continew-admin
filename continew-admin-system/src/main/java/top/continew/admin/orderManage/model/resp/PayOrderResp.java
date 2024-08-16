package top.continew.admin.orderManage.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 支付订单信息
 *
 * @author Jhon sam
 * @since 2024/08/15 11:08
 */
@Data
@Schema(description = "支付订单信息")
public class PayOrderResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 支付订单号
     */
    @Schema(description = "支付订单号")
    private String payOrderNo;

    /**
     * 商户名称
     */
    @Schema(description = "商户名称")
    private String mchName;

    /**
     * 商户订单号
     */
    @Schema(description = "商户订单号")
    private String mchOrderNo;

    /**
     * 流水号
     */
    @Schema(description = "流水号")
    private String traceNo;

    /**
     * 支付方式代码
     */
    @Schema(description = "支付方式代码")
    private String payMethodCode;

    /**
     * 支付金额
     */
    @Schema(description = "支付金额")
    private Long amount;

    /**
     * 支付金额，单位（分）
     */
    @Schema(description = "支付金额，单位（分）")
    private Integer payAmount;

    /**
     * 商户手续费
     */
    @Schema(description = "商户手续费")
    private Long mchFeeAmount;

    /**
     * 货币代码
     */
    @Schema(description = "货币代码")
    private String currency;

    /**
     * 支付状态: 0-订单生成, 1-支付中, 2-支付成功, 3-支付失败, 4-已撤销, 5-已退款, 6-订单关闭
     */
    @Schema(description = "支付状态: 0-订单生成, 1-支付中, 2-支付成功, 3-支付失败, 4-已撤销, 5-已退款, 6-订单关闭")
    private Integer status;

    /**
     * 向下游回调状态, 0-未发送,  1-已发送
     */
    @Schema(description = "向下游回调状态, 0-未发送,  1-已发送")
    private Integer notifyStatus;

    /**
     * 退款总金额,单位分
     */
    @Schema(description = "退款总金额,单位分")
    private Long refundAmount;

    /**
     * 订单支付成功时间
     */
    @Schema(description = "订单支付成功时间")
    private LocalDateTime successTime;

}