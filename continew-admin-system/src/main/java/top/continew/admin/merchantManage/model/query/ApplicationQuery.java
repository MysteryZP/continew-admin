package top.continew.admin.merchantManage.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

/**
 * 应用管理查询条件
 *
 * @author Jhon sam
 * @since 2024/08/01 15:36
 */
@Data
@Schema(description = "应用管理查询条件")
public class ApplicationQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用编号
     */
    @Schema(description = "应用编号")
    @Query(type = QueryType.LIKE)
    private String appNo;

    /**
     * 应用名称
     */
    @Schema(description = "应用名称")
    @Query(type = QueryType.LIKE)
    private String appName;

    /**
     * 商户ID
     */
    @Schema(description = "商户号")
    @Query(type = QueryType.EQ)
    private String mchNo;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @Query(type = QueryType.EQ)
    private Integer status;
}