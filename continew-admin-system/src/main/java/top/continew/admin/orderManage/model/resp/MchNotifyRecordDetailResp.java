package top.continew.admin.orderManage.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 商户通知详情信息
 *
 * @author Jhon sam
 * @since 2024/08/15 11:19
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "商户通知详情信息")
public class MchNotifyRecordDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @Schema(description = "订单ID")
    @ExcelProperty(value = "订单ID")
    private String payOrderNo;

    /**
     * 订单类型:1-支付,2-退款
     */
    @Schema(description = "订单类型:1-支付,2-退款")
    @ExcelProperty(value = "订单类型:1-支付,2-退款")
    private Integer orderType;

    /**
     * 商户订单号
     */
    @Schema(description = "商户订单号")
    @ExcelProperty(value = "商户订单号")
    private String mchOrderNo;

    /**
     * 商户号
     */
    @Schema(description = "商户号")
    @ExcelProperty(value = "商户号")
    private String mchNo;

    /**
     * 服务商号
     */
    @Schema(description = "服务商号")
    @ExcelProperty(value = "服务商号")
    private String isvNo;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @ExcelProperty(value = "应用ID")
    private String appNo;

    /**
     * 通知地址
     */
    @Schema(description = "通知地址")
    @ExcelProperty(value = "通知地址")
    private String notifyUrl;

    /**
     * 通知响应结果
     */
    @Schema(description = "通知响应结果")
    @ExcelProperty(value = "通知响应结果")
    private String resResult;

    /**
     * 通知次数
     */
    @Schema(description = "通知次数")
    @ExcelProperty(value = "通知次数")
    private Integer notifyCount;

    /**
     * 最大通知次数, 默认6次
     */
    @Schema(description = "最大通知次数, 默认6次")
    @ExcelProperty(value = "最大通知次数, 默认6次")
    private Integer notifyCountLimit;

    /**
     * 通知状态,1-通知中,2-通知成功,3-通知失败
     */
    @Schema(description = "通知状态,1-通知中,2-通知成功,3-通知失败")
    @ExcelProperty(value = "通知状态,1-通知中,2-通知成功,3-通知失败")
    private Integer status;

    /**
     * 最后一次通知时间
     */
    @Schema(description = "最后一次通知时间")
    @ExcelProperty(value = "最后一次通知时间")
    private LocalDateTime lastNotifyTime;

    /**
     * 订单创建时间
     */
    @Schema(description = "订单创建时间")
    @ExcelProperty(value = "订单创建时间")
    private LocalDateTime createTime;

    /**
     * 订单更新时间
     */
    @Schema(description = "订单更新时间")
    @ExcelProperty(value = "订单更新时间")
    private LocalDateTime updateTime;
}