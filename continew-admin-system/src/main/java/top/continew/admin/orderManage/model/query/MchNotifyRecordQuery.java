package top.continew.admin.orderManage.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

/**
 * 商户通知查询条件
 *
 * @author Jhon sam
 * @since 2024/08/15 11:19
 */
@Data
@Schema(description = "商户通知查询条件")
public class MchNotifyRecordQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @Schema(description = "订单ID")
    @Query(type = QueryType.EQ)
    private String payOrderNo;

    /**
     * 订单类型:1-支付,2-退款
     */
    @Schema(description = "订单类型:1-支付,2-退款")
    @Query(type = QueryType.EQ)
    private Integer orderType;

    /**
     * 商户订单号
     */
    @Schema(description = "商户订单号")
    @Query(type = QueryType.EQ)
    private String mchOrderNo;

    /**
     * 商户号
     */
    @Schema(description = "商户号")
    @Query(type = QueryType.EQ)
    private String mchNo;

}