package top.continew.admin.merchantManage.model.req;

import java.io.Serial;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改商户支付方式配置信息
 *
 * @author Jhon sam
 * @since 2024/08/06 17:39
 */
@Data
@Schema(description = "创建或修改商户支付方式配置信息")
public class MerchantPayMethodReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商户ID
     */
    @Schema(description = "商户号")
    @NotNull(message = "商户号不能为空")
    private String mchNo;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @NotNull(message = "应用ID不能为空")
    private String appNo;

    /**
     * 支付代码
     */
    @Schema(description = "支付代码")
    @NotBlank(message = "支付代码不能为空")
    @Length(max = 50, message = "支付代码长度不能超过 {max} 个字符")
    private String payCode;

    /**
     * 支付方式代码
     */
    @Schema(description = "支付方式代码")
    @NotBlank(message = "支付方式代码不能为空")
    @Length(max = 50, message = "支付方式代码长度不能超过 {max} 个字符")
    private String payMethodCode;

    /**
     * 费率
     */
    @Schema(description = "费率")
    @NotNull(message = "费率不能为空")
    private BigDecimal fee;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @NotNull(message = "状态不能为空")
    private Integer status;
}