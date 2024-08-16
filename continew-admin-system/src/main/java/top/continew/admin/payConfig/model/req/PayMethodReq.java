package top.continew.admin.payConfig.model.req;

import java.io.Serial;
import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改支付方式配置信息
 *
 * @author Jhon sam
 * @since 2024/08/02 10:46
 */
@Data
@Schema(description = "创建或修改支付方式配置信息")
public class PayMethodReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 支付方式代码
     */
    @Schema(description = "支付方式代码")
    @NotBlank(message = "支付方式代码不能为空")
    @Length(max = 50, message = "支付方式代码长度不能超过 {max} 个字符")
    private String code;

    /**
     * 支付方式名称
     */
    @Schema(description = "支付方式名称")
    @NotBlank(message = "支付方式名称不能为空")
    @Length(max = 100, message = "支付方式名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @NotNull(message = "状态不能为空")
    private Integer status;
}