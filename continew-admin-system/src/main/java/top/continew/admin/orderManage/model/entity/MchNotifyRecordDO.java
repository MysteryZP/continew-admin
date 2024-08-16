package top.continew.admin.orderManage.model.entity;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 商户通知实体
 *
 * @author Jhon sam
 * @since 2024/08/15 11:19
 */
@Data
@TableName("sys_mch_notify_record")
public class MchNotifyRecordDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private String payOrderNo;

    /**
     * 订单类型:1-支付,2-退款
     */
    private Integer orderType;

    /**
     * 商户订单号
     */
    private String mchOrderNo;

    /**
     * 商户号
     */
    private String mchNo;

    /**
     * 服务商号
     */
    private String isvNo;

    /**
     * 应用ID
     */
    private String appNo;

    /**
     * 通知地址
     */
    private String notifyUrl;

    /**
     * 通知响应结果
     */
    private String resResult;

    /**
     * 通知次数
     */
    private Integer notifyCount;

    /**
     * 最大通知次数, 默认6次
     */
    private Integer notifyCountLimit;

    /**
     * 通知状态,1-通知中,2-通知成功,3-通知失败
     */
    private Integer status;

    /**
     * 最后一次通知时间
     */
    private LocalDateTime lastNotifyTime;

}