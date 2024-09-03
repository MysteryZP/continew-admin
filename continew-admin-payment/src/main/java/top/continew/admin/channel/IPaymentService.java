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
package top.continew.admin.channel;

import org.springframework.stereotype.Component;
import top.continew.admin.model.req.UnifiedOrderReq;
import top.continew.admin.model.resp.AbstractResp;
import top.continew.admin.model.resp.MchAppConfigContextResp;
import top.continew.admin.orderManage.model.entity.PayOrderDO;

/*
* 调起上游渠道侧支付接口
*
* @author terrfly
* @site https://www.jeequan.com
* @date 2021/5/8 15:13
*/
@Component
public interface IPaymentService {

    /** 获取到接口code **/
    String getPayCode();

    /** 是否支持该支付方式 */
    boolean isSupport(String wayCode);

    /** 前置检查如参数等信息是否符合要求， 返回错误信息或直接抛出异常即可  */
    String preCheck(UnifiedOrderReq unifiedOrderReq, PayOrderDO payOrder);

    /** 自定义支付订单号， 若返回空则使用系统生成订单号 */
    String customPayOrderId(UnifiedOrderReq unifiedOrderReq, PayOrderDO payOrder, MchAppConfigContextResp mchAppConfigContext);


    /** 调起支付接口，并响应数据；  内部处理普通商户和服务商模式  **/
    AbstractResp pay(UnifiedOrderReq unifiedOrderReq, PayOrderDO payOrder, MchAppConfigContextResp mchAppConfigContext);

}