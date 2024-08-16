package top.continew.admin.orderManage.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 支付订单详情信息
 *
 * @author Jhon sam
 * @since 2024/08/15 11:08
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "支付订单详情信息")
public class PayOrderDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 支付订单号
     */
    @Schema(description = "支付订单号")
    @ExcelProperty(value = "支付订单号")
    private String payOrderNo;

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
     * 商户名称
     */
    @Schema(description = "商户名称")
    @ExcelProperty(value = "商户名称")
    private String mchName;

    /**
     * 类型: 1-普通商户, 2-特约商户(服务商模式)
     */
    @Schema(description = "类型: 1-普通商户, 2-特约商户(服务商模式)")
    @ExcelProperty(value = "类型: 1-普通商户, 2-特约商户(服务商模式)")
    private Integer mchType;

    /**
     * 商户订单号
     */
    @Schema(description = "商户订单号")
    @ExcelProperty(value = "商户订单号")
    private String mchOrderNo;

    /**
     * 流水号
     */
    @Schema(description = "流水号")
    @ExcelProperty(value = "流水号")
    private String traceNo;

    /**
     * 支付接口代码
     */
    @Schema(description = "支付接口代码")
    @ExcelProperty(value = "支付接口代码")
    private String payCode;

    /**
     * 支付方式代码
     */
    @Schema(description = "支付方式代码")
    @ExcelProperty(value = "支付方式代码")
    private String payMethodCode;

    /**
     * 支付金额
     */
    @Schema(description = "支付金额")
    @ExcelProperty(value = "支付金额")
    private Long amount;

    /**
     * 支付金额，单位（分）
     */
    @Schema(description = "支付金额，单位（分）")
    @ExcelProperty(value = "支付金额，单位（分）")
    private Integer payAmount;

    /**
     * 商户手续费费率快照
     */
    @Schema(description = "商户手续费费率快照")
    @ExcelProperty(value = "商户手续费费率快照")
    private BigDecimal mchFeeRate;

    /**
     * 商户手续费
     */
    @Schema(description = "商户手续费")
    @ExcelProperty(value = "商户手续费")
    private Long mchFeeAmount;

    /**
     * 货币代码
     */
    @Schema(description = "货币代码")
    @ExcelProperty(value = "货币代码")
    private String currency;

    /**
     * 支付状态: 0-订单生成, 1-支付中, 2-支付成功, 3-支付失败, 4-已撤销, 5-已退款, 6-订单关闭
     */
    @Schema(description = "支付状态: 0-订单生成, 1-支付中, 2-支付成功, 3-支付失败, 4-已撤销, 5-已退款, 6-订单关闭")
    @ExcelProperty(value = "支付状态: 0-订单生成, 1-支付中, 2-支付成功, 3-支付失败, 4-已撤销, 5-已退款, 6-订单关闭")
    private Integer status;

    /**
     * 向下游回调状态, 0-未发送,  1-已发送
     */
    @Schema(description = "向下游回调状态, 0-未发送,  1-已发送")
    @ExcelProperty(value = "向下游回调状态, 0-未发送,  1-已发送")
    private Integer notifyStatus;

    /**
     * 客户端IP
     */
    @Schema(description = "客户端IP")
    @ExcelProperty(value = "客户端IP")
    private String clientIp;

    /**
     * 商品标题
     */
    @Schema(description = "商品标题")
    @ExcelProperty(value = "商品标题")
    private String subject;

    /**
     * 商品描述信息
     */
    @Schema(description = "商品描述信息")
    @ExcelProperty(value = "商品描述信息")
    private String body;

    /**
     * 特定渠道发起额外参数
     */
    @Schema(description = "特定渠道发起额外参数")
    @ExcelProperty(value = "特定渠道发起额外参数")
    private String channelExtra;

    /**
     * 渠道用户标识,如微信openId,支付宝账号
     */
    @Schema(description = "渠道用户标识,如微信openId,支付宝账号")
    @ExcelProperty(value = "渠道用户标识,如微信openId,支付宝账号")
    private String channelUser;

    /**
     * 渠道订单号
     */
    @Schema(description = "渠道订单号")
    @ExcelProperty(value = "渠道订单号")
    private String channelOrderNo;

    /**
     * 退款状态: 0-未发生实际退款, 1-部分退款, 2-全额退款
     */
    @Schema(description = "退款状态: 0-未发生实际退款, 1-部分退款, 2-全额退款")
    @ExcelProperty(value = "退款状态: 0-未发生实际退款, 1-部分退款, 2-全额退款")
    private Integer refundStatus;

    /**
     * 退款次数
     */
    @Schema(description = "退款次数")
    @ExcelProperty(value = "退款次数")
    private Integer refundTimes;

    /**
     * 退款总金额,单位分
     */
    @Schema(description = "退款总金额,单位分")
    @ExcelProperty(value = "退款总金额,单位分")
    private Long refundAmount;

    /**
     * 订单分账模式：0-该笔订单不允许分账, 1-支付成功按配置自动完成分账, 2-商户手动分账(解冻商户金额)
     */
    @Schema(description = "订单分账模式：0-该笔订单不允许分账, 1-支付成功按配置自动完成分账, 2-商户手动分账(解冻商户金额)")
    @ExcelProperty(value = "订单分账模式：0-该笔订单不允许分账, 1-支付成功按配置自动完成分账, 2-商户手动分账(解冻商户金额)")
    private Integer divisionMode;

    /**
     * 订单分账状态：0-未发生分账, 1-等待分账任务处理, 2-分账处理中, 3-分账任务已结束(不体现状态)
     */
    @Schema(description = "订单分账状态：0-未发生分账, 1-等待分账任务处理, 2-分账处理中, 3-分账任务已结束(不体现状态)")
    @ExcelProperty(value = "订单分账状态：0-未发生分账, 1-等待分账任务处理, 2-分账处理中, 3-分账任务已结束(不体现状态)")
    private Integer divisionStatus;

    /**
     * 最新分账时间
     */
    @Schema(description = "最新分账时间")
    @ExcelProperty(value = "最新分账时间")
    private LocalDateTime divisionLastTime;

    /**
     * 渠道支付错误码
     */
    @Schema(description = "渠道支付错误码")
    @ExcelProperty(value = "渠道支付错误码")
    private String errCode;

    /**
     * 渠道支付错误描述
     */
    @Schema(description = "渠道支付错误描述")
    @ExcelProperty(value = "渠道支付错误描述")
    private String errMsg;

    /**
     * 商户扩展参数
     */
    @Schema(description = "商户扩展参数")
    @ExcelProperty(value = "商户扩展参数")
    private String extParam;

    /**
     * 异步通知地址
     */
    @Schema(description = "异步通知地址")
    @ExcelProperty(value = "异步通知地址")
    private String notifyUrl;

    /**
     * 页面跳转地址
     */
    @Schema(description = "页面跳转地址")
    @ExcelProperty(value = "页面跳转地址")
    private String returnUrl;

    /**
     * 订单失效时间
     */
    @Schema(description = "订单失效时间")
    @ExcelProperty(value = "订单失效时间")
    private LocalDateTime expiredTime;

    /**
     * 订单支付成功时间
     */
    @Schema(description = "订单支付成功时间")
    @ExcelProperty(value = "订单支付成功时间")
    private LocalDateTime successTime;

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