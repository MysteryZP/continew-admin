package top.continew.admin.orderManage.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 商户通知信息
 *
 * @author Jhon sam
 * @since 2024/08/15 11:19
 */
@Data
@Schema(description = "商户通知信息")
public class MchNotifyRecordResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @Schema(description = "订单ID")
    private String payOrderNo;

    /**
     * 订单类型:1-支付,2-退款
     */
    @Schema(description = "订单类型:1-支付,2-退款")
    private Integer orderType;

    /**
     * 商户号
     */
    @Schema(description = "商户号")
    private String mchNo;

    /**
     * 服务商号
     */
    @Schema(description = "服务商号")
    private String isvNo;

    /**
     * 通知次数
     */
    @Schema(description = "通知次数")
    private Integer notifyCount;

    /**
     * 最大通知次数, 默认6次
     */
    @Schema(description = "最大通知次数, 默认6次")
    private Integer notifyCountLimit;

    /**
     * 通知状态,1-通知中,2-通知成功,3-通知失败
     */
    @Schema(description = "通知状态,1-通知中,2-通知成功,3-通知失败")
    private Integer status;

    /**
     * 最后一次通知时间
     */
    @Schema(description = "最后一次通知时间")
    private LocalDateTime lastNotifyTime;

}