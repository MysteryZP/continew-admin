package top.continew.admin.merchantManage.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

/**
 * 商户支付方式配置查询条件
 *
 * @author Jhon sam
 * @since 2024/08/06 17:39
 */
@Data
@Schema(description = "商户支付方式配置查询条件")
public class MerchantPayMethodQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商户号
     */
    @Schema(description = "商户号")
    @Query(type = QueryType.LIKE)
    private String mchNo;

    /**
     * 应用ID
     */
    @Schema(description = "应用号")
    @Query(type = QueryType.EQ)
    private String appNo;

    /**
     * 支付代码
     */
    @Schema(description = "支付代码")
    @Query(type = QueryType.LIKE)
    private String payCode;

    /**
     * 支付方式代码
     */
    @Schema(description = "支付方式代码")
    @Query(type = QueryType.LIKE)
    private String payMethodCode;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @Query(type = QueryType.EQ)
    private Integer status;
}