/*
 * Copyright (c) 2021-2031, 河北计全科技有限公司 (https://www.jeequan.com & jeequan@126.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package top.continew.admin.model.req;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import top.continew.admin.common.constant.PayConstants;
import top.continew.admin.common.model.req.AbstractMchAppReq;
import top.continew.admin.model.req.payMethod.DefaultReq;

/*
* 创建订单请求参数对象
* 聚合支付接口（统一下单）
*
* @author terrfly
* @site https://www.jeequan.com
* @date 2021/6/8 17:33
*/
@Data
@Schema(description = "统一下单")
public class UnifiedOrderReq extends AbstractMchAppReq {

    /** 商户订单号 **/
    @Schema(description = "商户订单号", example = "MCH123123123")
    @NotBlank(message="商户订单号不能为空")
    private String mchOrderNo;

    /** 支付方式  如： wxpay_jsapi,alipay_wap等   **/
    @Schema(description = "支付接口代码", example = "alipay")
    @NotBlank(message="支付接口代码不能为空")
    private String payCode;

    /** 支付方式  如： wxpay_jsapi,alipay_wap等   **/
    @Schema(description = "支付方法", example = "ALIPAY_FACE")
    @NotBlank(message="支付方法不能为空")
    private String payMethodCode;

    /** 支付金额， 单位：分 **/
    @Schema(description = "支付金额（单位：分）", example = "100")
    @NotNull(message="支付金额不能为空")
    @Min(value = 1, message = "支付金额不能为空")
    private Long amount;

    /** 货币代码 **/
    @Schema(description = "货币代码", example = "CNY")
    @NotBlank(message="货币代码不能为空")
    private String currency;

    /** 客户端IP地址 **/
    private String clientIp;

    /** 商品标题 **/
    @Schema(description = "商品标题", example = "牙刷")
    @NotBlank(message="商品标题不能为空")
    private String subject;

    /** 商品描述信息 **/
    @Schema(description = "商品描述", example = "高露洁牙刷1把")
    @NotBlank(message="商品描述信息不能为空")
    private String body;

    /** 异步通知地址 **/
    @Schema(description = "异步通知地址", example = "https://www.xxx.xxx/api/x/x")
    @NotBlank(message="异步通知地址不能为空")
    @Pattern(
        regexp = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})([/\\w .-]*)*/?$",
        message = "异步通知地址协议仅支持http:// 或 https:// "
    )
    private String notifyUrl;

    /** 跳转通知地址 **/
    @Schema(description = "跳转通知地址", example = "https://www.xxx.xxx/api/x/x")
    @Pattern(
        regexp = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})([/\\w .-]*)*/?$",
        message = "跳转通知地址协议仅支持http:// 或 https:// "
    )
    private String returnUrl;

    /** 订单失效时间, 单位：秒 **/
    @Schema(description = "订单失效时间（秒）", example = "300")
    private Integer expiredTime;

    /** 特定渠道发起额外参数 **/
    @Schema(description = "特定渠道发起额外参数", example = "{\"name\":\"zhangsan\"}")
    private String channelExtra;

    /** 商户扩展参数 **/
    @Schema(description = "商户扩展参数", example = "{\"name\":\"zhangsan\"}")
    private String extParam;

    /** 分账模式： 0-该笔订单不允许分账, 1-支付成功按配置自动完成分账, 2-商户手动分账(解冻商户金额) **/
    @Schema(description = "分账模式：0-该笔订单不允许分账, 1-支付成功按配置自动完成分账, 2-商户手动分账(解冻商户金额)", example = "1")
    @Range(min = 0, max = 2, message = "分账模式设置值有误")
    private Integer divisionMode;

    /** 返回真实的bizRQ **/
    public UnifiedOrderReq buildBizRQ(){

        if(PayConstants.PAY_METHOD_CODE.DEFAULT.equals(payCode)){
            DefaultReq bizReq = JSONObject.parseObject(StringUtils.defaultIfEmpty(this.channelExtra, "{}"), DefaultReq.class);
            BeanUtils.copyProperties(this, bizReq);
            return bizReq;
        }
        return this;
    }

    /** 获取渠道用户ID **/
    @JSONField(serialize = false)
    public String getChannelUserId(){
        return null;
    }

}
