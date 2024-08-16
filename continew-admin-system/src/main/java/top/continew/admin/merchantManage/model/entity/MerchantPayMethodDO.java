package top.continew.admin.merchantManage.model.entity;

import java.io.Serial;
import java.math.BigDecimal;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 商户支付方式配置实体
 *
 * @author Jhon sam
 * @since 2024/08/06 17:39
 */
@Data
@TableName("sys_merchant_pay_method")
public class MerchantPayMethodDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商户ID
     */
    private String mchNo;

    /**
     * 应用ID
     */
    private String appNo;

    /**
     * 支付代码
     */
    private String payCode;

    /**
     * 支付方式代码
     */
    private String payMethodCode;

    /**
     * 费率
     */
    private BigDecimal fee;

    /**
     * 状态
     */
    private Integer status;
}