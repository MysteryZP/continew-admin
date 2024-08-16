package top.continew.admin.merchantManage.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 应用管理实体
 *
 * @author Jhon sam
 * @since 2024/08/01 15:36
 */
@Data
@TableName("sys_application")
public class ApplicationDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用编号
     */
    private String appNo;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * APP秘钥
     */
    private String appSecret;

    /**
     * 商户号
     */
    private String mchNo;

    /**
     * 状态
     */
    private Integer status;
}