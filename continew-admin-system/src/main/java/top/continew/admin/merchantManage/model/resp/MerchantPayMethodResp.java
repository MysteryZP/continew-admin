package top.continew.admin.merchantManage.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 商户支付方式配置信息
 *
 * @author Jhon sam
 * @since 2024/08/06 17:39
 */
@Data
@Schema(description = "商户支付方式配置信息")
public class MerchantPayMethodResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商户号
     */
    @Schema(description = "商户ID")
    private String mchNo;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appNo;

    /**
     * 支付代码
     */
    @Schema(description = "支付代码")
    private String payCode;

    /**
     * 支付方式代码
     */
    @Schema(description = "支付方式代码")
    private String payMethodCode;

    /**
     * 费率
     */
    @Schema(description = "费率")
    private BigDecimal fee;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Integer status;
}