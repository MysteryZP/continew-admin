package top.continew.admin.payConfig.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

/**
 * 支付方式配置查询条件
 *
 * @author Jhon sam
 * @since 2024/08/02 10:46
 */
@Data
@Schema(description = "支付方式配置查询条件")
public class PayMethodQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 支付方式代码
     */
    @Schema(description = "支付方式代码")
    @Query(type = QueryType.LIKE)
    private String code;

    /**
     * 支付代码
     */
    @Schema(description = "支付代码")
    @Query(type = QueryType.LIKE)
    private String payCode;


    /**
     * 支付方式名称
     */
    @Schema(description = "支付方式名称")
    @Query(type = QueryType.LIKE)
    private String name;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @Query(type = QueryType.EQ)
    private Integer status;
}