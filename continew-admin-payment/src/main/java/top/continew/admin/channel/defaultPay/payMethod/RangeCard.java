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
package top.continew.admin.channel.defaultPay.payMethod;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.ranges.Range;
import top.continew.admin.channel.defaultPay.DefaultPaymentService;
import top.continew.admin.common.enums.OrderStatusEnum;
import top.continew.admin.common.resp.ChannelResp;
import top.continew.admin.model.req.UnifiedOrderReq;
import top.continew.admin.model.resp.AbstractResp;
import top.continew.admin.model.resp.MchAppConfigContextResp;
import top.continew.admin.model.resp.payMethod.RangeCardOrderResp;
import top.continew.admin.orderManage.model.entity.PayOrderDO;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
* 银行卡轮训
*
* @author terrfly
* @site https://www.jeequan.com
* @date 2021/6/8 17:21
*/
@Service("defaultPaymentByRangeCardService") //Service Name需保持全局唯一性
@Slf4j
public class RangeCard extends DefaultPaymentService {

    @Override
    public String preCheck(UnifiedOrderReq rq, PayOrderDO payOrder) {
        return null;
    }

    @Override
    public AbstractResp pay(UnifiedOrderReq req, PayOrderDO payOrder, MchAppConfigContextResp mchAppConfigContext){
        // 生成随机的UUID
        UUID uuid = UUID.randomUUID();
        String randomString = uuid.toString().replace("-", "");
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("nonceStr",randomString);
        paramMap.put("mchOrderNo",payOrder.getId());
        paramMap.put("notifyUrl","http://www.baidu.com");
        paramMap.put("amount",payOrder.getAmount());
        paramMap.put("body",payOrder.getAmount());
        paramMap.put("email","ip_ymx@163.com");
        // 构造函数响应数据
        RangeCardOrderResp rangeCardOrderResp = new RangeCardOrderResp();
        rangeCardOrderResp.setPayDataType("Range_Card");
        rangeCardOrderResp.setPayData("http://www.baidu.com");//支付页面地址
        rangeCardOrderResp.setPayOrderId(payOrder.getPayOrderNo());
        rangeCardOrderResp.setMchOrderNo(payOrder.getMchNo());
        rangeCardOrderResp.setOrderState(OrderStatusEnum.SUCCESS.getValue());
        //设置渠道返回参数

        return rangeCardOrderResp;
    }

}
