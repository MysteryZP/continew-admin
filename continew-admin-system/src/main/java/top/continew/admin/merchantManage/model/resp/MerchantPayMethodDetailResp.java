package top.continew.admin.merchantManage.model.resp;

import java.io.Serial;
import java.math.BigDecimal;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 商户支付方式配置详情信息
 *
 * @author Jhon sam
 * @since 2024/08/06 17:39
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "商户支付方式配置详情信息")
public class MerchantPayMethodDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    /**
     * 商户ID
     */
    @Schema(description = "商户ID")
    @ExcelProperty(value = "商户ID")
    private String mchNo;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @ExcelProperty(value = "应用ID")
    private String appNo;

    /**
     * 支付代码
     */
    @Schema(description = "支付代码")
    @ExcelProperty(value = "支付代码")
    private String payCode;

    /**
     * 支付方式代码
     */
    @Schema(description = "支付方式代码")
    @ExcelProperty(value = "支付方式代码")
    private String payMethodCode;

    /**
     * 费率
     */
    @Schema(description = "费率")
    @ExcelProperty(value = "费率")
    private BigDecimal fee;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @ExcelProperty(value = "状态")
    private Integer status;
}