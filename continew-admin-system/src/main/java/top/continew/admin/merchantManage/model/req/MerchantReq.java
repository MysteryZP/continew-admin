package top.continew.admin.merchantManage.model.req;

import java.io.Serial;
import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改商户管理信息
 *
 * @author Jhon sam
 * @since 2024/07/30 16:14
 */
@Data
@Schema(description = "创建或修改商户管理信息")
public class MerchantReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 联系人姓名
     */
    @Schema(description = "联系人姓名")
    @NotBlank(message = "联系人姓名不能为空")
    @Length(max = 45, message = "联系人姓名长度不能超过 {max} 个字符")
    private String contactName;

    /**
     * 商户名称
     */
    @Schema(description = "商户名称")
    @NotBlank(message = "商户名称不能为空")
    @Length(max = 255, message = "商户名称长度不能超过 {max} 个字符")
    private String mchName;

    /**
     * 登录名
     */
    @Schema(description = "登录名")
    @NotBlank(message = "登录名不能为空")
    @Length(max = 255, message = "登录名长度不能超过 {max} 个字符")
    private String username;

    /**
     * 类型
     */
    @Schema(description = "类型")
    @NotNull(message = "类型不能为空")
    private Integer type;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    @NotBlank(message = "联系电话不能为空")
    @Length(max = 20, message = "联系电话长度不能超过 {max} 个字符")
    private String contactTel;

    /**
     * 商户简称
     */
    @Schema(description = "商户简称")
    @Length(max = 50, message = "商户简称长度不能超过 {max} 个字符")
    private String mchShortName;

    /**
     * 初始用户ID
     */
    @Schema(description = "初始用户ID")
    private Long initUserId;

    /**
     * 状态 （1：启用；2：禁用；3：锁定）
     */
    @Schema(description = "状态 （1：启用；2：禁用；3：锁定）")
    @NotNull(message = "状态 （1：启用；2：禁用；3：锁定）不能为空")
    private Integer status;
}