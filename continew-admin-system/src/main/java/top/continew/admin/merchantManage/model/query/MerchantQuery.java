package top.continew.admin.merchantManage.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

/**
 * 商户管理查询条件
 *
 * @author Jhon sam
 * @since 2024/07/30 16:14
 */
@Data
@Schema(description = "商户管理查询条件")
public class MerchantQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商户编号
     */
    @Schema(description = "商户编号")
    @Query(type = QueryType.LIKE)
    private String mchNo;

    /**
     * 商户名称
     */
    @Schema(description = "商户名称")
    @Query(type = QueryType.LIKE)
    private String mchName;

    /**
     * 状态 （1：启用；2：禁用；3：锁定）
     */
    @Schema(description = "状态 （1：启用；2：禁用；3：锁定）")
    @Query(type = QueryType.EQ)
    private Integer status;
}