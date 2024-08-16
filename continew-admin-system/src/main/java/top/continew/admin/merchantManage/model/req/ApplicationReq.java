package top.continew.admin.merchantManage.model.req;

import java.io.Serial;
import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改应用管理信息
 *
 * @author Jhon sam
 * @since 2024/08/01 15:36
 */
@Data
@Schema(description = "创建或修改应用管理信息")
public class ApplicationReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用名称
     */
    @Schema(description = "应用名称")
    @NotBlank(message = "应用名称不能为空")
    @Length(max = 255, message = "应用名称长度不能超过 {max} 个字符")
    private String appName;

    /**
     * APP秘钥
     */
    @Schema(description = "APP秘钥")
    @Length(max = 45, message = "APP秘钥长度不能超过 {max} 个字符")
    private String appSecret;

    /**
     * 商户ID
     */
    @Schema(description = "商户号")
    @NotNull(message = "商户号不能为空")
    private String mchNo;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @NotNull(message = "状态不能为空")
    private Integer status;
}