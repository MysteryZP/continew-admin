package top.continew.admin.common.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Peng Zhang
 * @description 商户统一入参
 * @since 2024/8/13 16:34
 */
@Data
public class AbstractMchAppReq {

    /** 商户号 **/
    @Schema(description = "商户号", example = "MCH123322221")
    @NotBlank(message="商户号不能为空")
    private String mchNo;

    /** 商户应用ID **/
    @Schema(description = "商户应用号", example = "APP93932929348")
    @NotBlank(message="商户应用号不能为空")
    private String appNo;
}
