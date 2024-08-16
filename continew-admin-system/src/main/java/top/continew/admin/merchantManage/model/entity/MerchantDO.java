package top.continew.admin.merchantManage.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 商户管理实体
 *
 * @author Jhon sam
 * @since 2024/07/30 16:14
 */
@Data
@TableName("sys_merchant")
public class MerchantDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商户编号
     */
    private String mchNo;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 商户名称
     */
    private String mchName;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 联系电话
     */
    private String contactTel;

    /**
     * 商户简称
     */
    private String mchShortName;

    /**
     * 初始用户ID
     */
    private Long initUserId;

    /**
     * 状态 （1：启用；2：禁用；3：锁定）
     */
    private Integer status;
}