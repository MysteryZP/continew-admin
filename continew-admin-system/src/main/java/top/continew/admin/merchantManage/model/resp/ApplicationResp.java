package top.continew.admin.merchantManage.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 应用管理信息
 *
 * @author Jhon sam
 * @since 2024/08/01 15:36
 */
@Data
@Schema(description = "应用管理信息")
public class ApplicationResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用编号
     */
    @Schema(description = "应用编号")
    private String appNo;

    /**
     * 应用编号
     */
    @Schema(description = "商户号")
    private String mchNo;

    /**
     * 应用名称
     */
    @Schema(description = "应用名称")
    private String appName;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Integer status;
}