package top.continew.admin.merchantManage.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

import cn.crane4j.annotation.Assemble;
import cn.crane4j.core.executor.handler.ManyToManyAssembleOperationHandler;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.admin.common.constant.ContainerConstants;
import top.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 商户管理信息
 *
 * @author Jhon sam
 * @since 2024/07/30 16:14
 */
@Data
@Schema(description = "商户管理信息")
public class MerchantResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商户编号
     */
    @Schema(description = "商户编号")
    private String mchNo;

    /**
     * 商户名称
     */
    @Schema(description = "商户名称")
    private String mchName;

    /**
     * 类型
     */
    @Schema(description = "类型")
    private Integer type;

    /**
     * 角色 ID 列表
     */
    @Schema(description = "角色 ID 列表", example = "2")
    private List<Long> roleIds;

    /**
     * 状态 （1：启用；2：禁用；3：锁定）
     */
    @Schema(description = "状态 （1：启用；2：禁用；3：锁定）")
    private Integer status;
}