package top.continew.admin.payConfig.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 支付方式配置实体
 *
 * @author Jhon sam
 * @since 2024/08/02 10:46
 */
@Data
@TableName("sys_pay_method")
public class PayMethodDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 支付方式代码
     */
    private String code;

    /**
     * 支付代码
     */
    private String payCode;

    /**
     * 支付方式名称
     */
    private String name;

    /**
     * 状态
     */
    private Integer status;
}