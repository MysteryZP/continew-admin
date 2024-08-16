package top.continew.admin.orderManage.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

/**
 * 支付订单查询条件
 *
 * @author Jhon sam
 * @since 2024/08/15 11:08
 */
@Data
@Schema(description = "支付订单查询条件")
public class PayOrderQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 支付订单号
     */
    @Schema(description = "支付订单号")
    @Query(type = QueryType.LIKE)
    private String payOrderNo;

    /**
     * 商户号
     */
    @Schema(description = "商户号")
    @Query(type = QueryType.LIKE_LEFT)
    private String mchNo;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Query(type = QueryType.EQ)
    private String appNo;

    /**
     * 商户订单号
     */
    @Schema(description = "商户订单号")
    @Query(type = QueryType.LIKE)
    private String mchOrderNo;

    /**
     * 流水号
     */
    @Schema(description = "流水号")
    @Query(type = QueryType.LIKE_LEFT)
    private String traceNo;

    /**
     * 支付接口代码
     */
    @Schema(description = "支付接口代码")
    @Query(type = QueryType.EQ)
    private String payCode;

    /**
     * 支付方式代码
     */
    @Schema(description = "支付方式代码")
    @Query(type = QueryType.EQ)
    private String payMethodCode;

    /**
     * 货币代码
     */
    @Schema(description = "货币代码")
    @Query(type = QueryType.EQ)
    private String currency;

    /**
     * 支付状态: 0-订单生成, 1-支付中, 2-支付成功, 3-支付失败, 4-已撤销, 5-已退款, 6-订单关闭
     */
    @Schema(description = "支付状态: 0-订单生成, 1-支付中, 2-支付成功, 3-支付失败, 4-已撤销, 5-已退款, 6-订单关闭")
    @Query(type = QueryType.EQ)
    private Integer status;

    /**
     * 向下游回调状态, 0-未发送,  1-已发送
     */
    @Schema(description = "向下游回调状态, 0-未发送,  1-已发送")
    @Query(type = QueryType.EQ)
    private Integer notifyStatus;

}