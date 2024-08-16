package top.continew.admin.payConfig.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 支付方式配置信息
 *
 * @author Jhon sam
 * @since 2024/08/02 10:46
 */
@Data
@Schema(description = "支付方式配置信息")
public class PayMethodResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 支付方式代码
     */
    @Schema(description = "支付方式代码")
    private String code;

    /**
     * 支付方式代码
     */
    @Schema(description = "支付代码")
    private String payCode;

    /**
     * 支付方式名称
     */
    @Schema(description = "支付方式名称")
    private String name;
}