package top.continew.admin.merchantManage.model.resp;

import java.io.Serial;

import cn.crane4j.annotation.Assemble;
import cn.crane4j.annotation.AssembleMethod;
import cn.crane4j.core.executor.handler.OneToOneAssembleOperationHandler;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import org.hibernate.validator.constraints.Length;
import top.continew.admin.common.constant.ContainerConstants;
import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 商户管理详情信息
 *
 * @author Jhon sam
 * @since 2024/07/30 16:14
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "商户管理详情信息")
public class MerchantDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商户编号
     */
    @Schema(description = "商户编号")
    @ExcelProperty(value = "商户编号")
    private String mchNo;

    /**
     * 联系人姓名
     */
    @Schema(description = "联系人姓名")
    @ExcelProperty(value = "联系人姓名")
    private String contactName;

    /**
     * 商户名称
     */
    @Schema(description = "商户名称")
    @ExcelProperty(value = "商户名称")
    private String mchName;

    /**
     * 类型
     */
    @Schema(description = "类型")
    @ExcelProperty(value = "类型")
    private Integer type;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    @ExcelProperty(value = "联系电话")
    private String contactTel;

    /**
     * 商户简称
     */
    @Schema(description = "商户简称")
    @ExcelProperty(value = "商户简称")
    private String mchShortName;

    /**
     * 初始用户ID
     */
    @Schema(description = "初始用户ID")
    @ExcelProperty(value = "初始用户ID")
    @Assemble(
        prop = ":username",
        container = ContainerConstants.USER_NAME,
        handlerType = OneToOneAssembleOperationHandler.class
    )
    private Long initUserId;

    /**
     * 登录名
     */
    @Schema(description = "登录名")
    @ExcelProperty(value = "登录名")
    private String username;

    /**
     * 状态 （1：启用；2：禁用；3：锁定）
     */
    @Schema(description = "状态 （1：启用；2：禁用；3：锁定）")
    @ExcelProperty(value = "状态 （1：启用；2：禁用；3：锁定）")
    private Integer status;
}