package top.continew.admin.orderManage.model.req;

import java.io.Serial;
import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改商户通知信息
 *
 * @author Jhon sam
 * @since 2024/08/15 11:19
 */
@Data
@Schema(description = "创建或修改商户通知信息")
public class MchNotifyRecordReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID")
    private Long id;

    /**
     * 订单ID
     */
    @Schema(description = "订单ID")
    @NotBlank(message = "订单ID不能为空")
    @Length(max = 64, message = "订单ID长度不能超过 {max} 个字符")
    private String payOrderNo;

    /**
     * 订单类型:1-支付,2-退款
     */
    @Schema(description = "订单类型:1-支付,2-退款")
    @NotNull(message = "订单类型:1-支付,2-退款不能为空")
    private Integer orderType;

    /**
     * 商户订单号
     */
    @Schema(description = "商户订单号")
    @NotBlank(message = "商户订单号不能为空")
    @Length(max = 64, message = "商户订单号长度不能超过 {max} 个字符")
    private String mchOrderNo;

    /**
     * 商户号
     */
    @Schema(description = "商户号")
    @NotBlank(message = "商户号不能为空")
    @Length(max = 64, message = "商户号长度不能超过 {max} 个字符")
    private String mchNo;

    /**
     * 服务商号
     */
    @Schema(description = "服务商号")
    @Length(max = 64, message = "服务商号长度不能超过 {max} 个字符")
    private String isvNo;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @NotBlank(message = "应用ID不能为空")
    @Length(max = 64, message = "应用ID长度不能超过 {max} 个字符")
    private String appNo;

    /**
     * 通知地址
     */
    @Schema(description = "通知地址")
    @NotBlank(message = "通知地址不能为空")
    @Length(max = 65535, message = "通知地址长度不能超过 {max} 个字符")
    private String notifyUrl;

    /**
     * 通知响应结果
     */
    @Schema(description = "通知响应结果")
    @Length(max = 65535, message = "通知响应结果长度不能超过 {max} 个字符")
    private String resResult;

    /**
     * 通知次数
     */
    @Schema(description = "通知次数")
    @NotNull(message = "通知次数不能为空")
    private Integer notifyCount;

    /**
     * 最大通知次数, 默认6次
     */
    @Schema(description = "最大通知次数, 默认6次")
    @NotNull(message = "最大通知次数, 默认6次不能为空")
    private Integer notifyCountLimit;

    /**
     * 通知状态,1-通知中,2-通知成功,3-通知失败
     */
    @Schema(description = "通知状态,1-通知中,2-通知成功,3-通知失败")
    @NotNull(message = "通知状态,1-通知中,2-通知成功,3-通知失败不能为空")
    private Integer status;

    /**
     * 最后一次通知时间
     */
    @Schema(description = "最后一次通知时间")
    private LocalDateTime lastNotifyTime;

    /**
     * 订单创建时间
     */
    @Schema(description = "订单创建时间")
    @NotNull(message = "订单创建时间不能为空")
    private LocalDateTime createTime;

    /**
     * 创建用户ID
     */
    @Schema(description = "创建用户ID")
    @NotNull(message = "创建用户ID不能为空")
    private Long createUser;

    /**
     * 订单更新时间
     */
    @Schema(description = "订单更新时间")
    private LocalDateTime updateTime;

    /**
     * 更新用户ID
     */
    @Schema(description = "更新用户ID")
    private Long updateUser;
}