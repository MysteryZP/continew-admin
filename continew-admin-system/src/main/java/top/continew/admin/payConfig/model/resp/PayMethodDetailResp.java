package top.continew.admin.payConfig.model.resp;

import java.io.Serial;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 支付方式配置详情信息
 *
 * @author Jhon sam
 * @since 2024/08/02 10:46
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "支付方式配置详情信息")
public class PayMethodDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 支付方式代码
     */
    @Schema(description = "支付方式代码")
    @ExcelProperty(value = "支付方式代码")
    private String code;

    /**
     * 支付代码
     */
    @Schema(description = "支付代码")
    @ExcelProperty(value = "支付代码")
    private String payCode;

    /**
     * 支付方式名称
     */
    @Schema(description = "支付方式名称")
    @ExcelProperty(value = "支付方式名称")
    private String name;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @ExcelProperty(value = "状态")
    private Integer status;
}