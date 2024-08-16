package top.continew.admin.merchantManage.model.resp;

import java.io.Serial;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 应用管理详情信息
 *
 * @author Jhon sam
 * @since 2024/08/01 15:36
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "应用管理详情信息")
public class ApplicationDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用编号
     */
    @Schema(description = "应用编号")
    @ExcelProperty(value = "应用编号")
    private String appNo;

    /**
     * 应用名称
     */
    @Schema(description = "应用名称")
    @ExcelProperty(value = "应用名称")
    private String appName;

    /**
     * APP秘钥
     */
    @Schema(description = "APP秘钥")
    @ExcelProperty(value = "APP秘钥")
    private String appSecret;

    /**
     * 商户ID
     */
    @Schema(description = "商户号")
    @ExcelProperty(value = "商户号")
    private String mchNo;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @ExcelProperty(value = "状态")
    private Integer status;
}