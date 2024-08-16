package top.continew.admin.orderManage.model.req;

import java.io.Serial;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改支付订单信息
 *
 * @author Jhon sam
 * @since 2024/08/15 11:08
 */
@Data
@Schema(description = "创建或修改支付订单信息")
public class PayOrderReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 支付订单号
     */
    @Schema(description = "支付订单号")
    @Length(max = 64, message = "支付订单号长度不能超过 {max} 个字符")
    private String payOrderNo;

    /**
     * 商户号
     */
    @Schema(description = "商户号")
    @NotBlank(message = "商户号不能为空")
    @Length(max = 64, message = "商户号长度不能超过 {max} 个字符")
    private String mchNo;

    /**
     * 服务商号
     */
    @Schema(description = "服务商号")
    @Length(max = 64, message = "服务商号长度不能超过 {max} 个字符")
    private String isvNo;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @NotBlank(message = "应用ID不能为空")
    @Length(max = 64, message = "应用ID长度不能超过 {max} 个字符")
    private String appNo;

    /**
     * 商户名称
     */
    @Schema(description = "商户名称")
    @Length(max = 30, message = "商户名称长度不能超过 {max} 个字符")
    private String mchName;

    /**
     * 类型: 1-普通商户, 2-特约商户(服务商模式)
     */
    @Schema(description = "类型: 1-普通商户, 2-特约商户(服务商模式)")
    private Integer mchType;

    /**
     * 商户订单号
     */
    @Schema(description = "商户订单号")
    @NotBlank(message = "商户订单号不能为空")
    @Length(max = 64, message = "商户订单号长度不能超过 {max} 个字符")
    private String mchOrderNo;

    /**
     * 流水号
     */
    @Schema(description = "流水号")
    @Length(max = 64, message = "流水号长度不能超过 {max} 个字符")
    private String traceNo;

    /**
     * 支付接口代码
     */
    @Schema(description = "支付接口代码")
    @NotBlank(message = "支付接口代码不能为空")
    @Length(max = 20, message = "支付接口代码长度不能超过 {max} 个字符")
    private String payCode;

    /**
     * 支付方式代码
     */
    @Schema(description = "支付方式代码")
    @NotBlank(message = "支付方式代码不能为空")
    @Length(max = 20, message = "支付方式代码长度不能超过 {max} 个字符")
    private String payMethodCode;

    /**
     * 支付金额
     */
    @Schema(description = "支付金额")
    @NotNull(message = "支付金额不能为空")
    private Long amount;

    /**
     * 支付金额，单位（分）
     */
    @Schema(description = "支付金额，单位（分）")
    private Integer payAmount;

    /**
     * 商户手续费费率快照
     */
    @Schema(description = "商户手续费费率快照")
    private BigDecimal mchFeeRate;

    /**
     * 商户手续费
     */
    @Schema(description = "商户手续费")
    private Long mchFeeAmount;

    /**
     * 货币代码
     */
    @Schema(description = "货币代码")
    @NotBlank(message = "货币代码不能为空")
    @Length(max = 3, message = "货币代码长度不能超过 {max} 个字符")
    private String currency;

    /**
     * 支付状态: 0-订单生成, 1-支付中, 2-支付成功, 3-支付失败, 4-已撤销, 5-已退款, 6-订单关闭
     */
    @Schema(description = "支付状态: 0-订单生成, 1-支付中, 2-支付成功, 3-支付失败, 4-已撤销, 5-已退款, 6-订单关闭")
    private Integer status;

    /**
     * 向下游回调状态, 0-未发送,  1-已发送
     */
    @Schema(description = "向下游回调状态, 0-未发送,  1-已发送")
    private Integer notifyStatus;

    /**
     * 客户端IP
     */
    @Schema(description = "客户端IP")
    @Length(max = 32, message = "客户端IP长度不能超过 {max} 个字符")
    private String clientIp;

    /**
     * 商品标题
     */
    @Schema(description = "商品标题")
    @NotBlank(message = "商品标题不能为空")
    @Length(max = 64, message = "商品标题长度不能超过 {max} 个字符")
    private String subject;

    /**
     * 商品描述信息
     */
    @Schema(description = "商品描述信息")
    @NotBlank(message = "商品描述信息不能为空")
    @Length(max = 256, message = "商品描述信息长度不能超过 {max} 个字符")
    private String body;

    /**
     * 特定渠道发起额外参数
     */
    @Schema(description = "特定渠道发起额外参数")
    @Length(max = 512, message = "特定渠道发起额外参数长度不能超过 {max} 个字符")
    private String channelExtra;

    /**
     * 渠道用户标识,如微信openId,支付宝账号
     */
    @Schema(description = "渠道用户标识,如微信openId,支付宝账号")
    @Length(max = 64, message = "渠道用户标识,如微信openId,支付宝账号长度不能超过 {max} 个字符")
    private String channelUser;

    /**
     * 渠道订单号
     */
    @Schema(description = "渠道订单号")
    @Length(max = 64, message = "渠道订单号长度不能超过 {max} 个字符")
    private String channelOrderNo;

    /**
     * 退款状态: 0-未发生实际退款, 1-部分退款, 2-全额退款
     */
    @Schema(description = "退款状态: 0-未发生实际退款, 1-部分退款, 2-全额退款")
    private Integer refundStatus;

    /**
     * 退款次数
     */
    @Schema(description = "退款次数")
    private Integer refundTimes;

    /**
     * 退款总金额,单位分
     */
    @Schema(description = "退款总金额,单位分")
    private Long refundAmount;

    /**
     * 订单分账模式：0-该笔订单不允许分账, 1-支付成功按配置自动完成分账, 2-商户手动分账(解冻商户金额)
     */
    @Schema(description = "订单分账模式：0-该笔订单不允许分账, 1-支付成功按配置自动完成分账, 2-商户手动分账(解冻商户金额)")
    private Integer divisionMode;

    /**
     * 订单分账状态：0-未发生分账, 1-等待分账任务处理, 2-分账处理中, 3-分账任务已结束(不体现状态)
     */
    @Schema(description = "订单分账状态：0-未发生分账, 1-等待分账任务处理, 2-分账处理中, 3-分账任务已结束(不体现状态)")
    private Integer divisionStatus;

    /**
     * 最新分账时间
     */
    @Schema(description = "最新分账时间")
    private LocalDateTime divisionLastTime;

    /**
     * 渠道支付错误码
     */
    @Schema(description = "渠道支付错误码")
    @Length(max = 128, message = "渠道支付错误码长度不能超过 {max} 个字符")
    private String errCode;

    /**
     * 渠道支付错误描述
     */
    @Schema(description = "渠道支付错误描述")
    @Length(max = 256, message = "渠道支付错误描述长度不能超过 {max} 个字符")
    private String errMsg;

    /**
     * 商户扩展参数
     */
    @Schema(description = "商户扩展参数")
    @Length(max = 128, message = "商户扩展参数长度不能超过 {max} 个字符")
    private String extParam;

    /**
     * 异步通知地址
     */
    @Schema(description = "异步通知地址")
    @NotBlank(message = "异步通知地址不能为空")
    @Length(max = 128, message = "异步通知地址长度不能超过 {max} 个字符")
    private String notifyUrl;

    /**
     * 页面跳转地址
     */
    @Schema(description = "页面跳转地址")
    @Length(max = 128, message = "页面跳转地址长度不能超过 {max} 个字符")
    private String returnUrl;

    /**
     * 订单失效时间
     */
    @Schema(description = "订单失效时间")
    private LocalDateTime expiredTime;

    /**
     * 订单支付成功时间
     */
    @Schema(description = "订单支付成功时间")
    private LocalDateTime successTime;


    /**
     * 创建用户ID
     */
    @Schema(description = "创建用户ID")
    private Long createUser;

    /**
     * 订单支付成功时间
     */
    @Schema(description = "订单创建时间")
    private LocalDateTime createTime;

    /**
     * 更新用户ID
     */
    @Schema(description = "更新用户ID")
    private Long updateUser;

    /**
     * 订单支付成功时间
     */
    @Schema(description = "订单修改时间")
    private LocalDateTime updateTime;
}