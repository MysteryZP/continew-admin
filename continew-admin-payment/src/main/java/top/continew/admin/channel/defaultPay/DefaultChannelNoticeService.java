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
package top.continew.admin.channel.defaultPay;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import top.continew.admin.channel.AbstractChannelNoticeService;
import top.continew.admin.common.constant.PayConstants;
import top.continew.admin.common.util.request.RequestKits;
import top.continew.admin.exception.ResponseException;
import top.continew.admin.model.msg.ChannelRetMsg;
import top.continew.admin.model.resp.MchAppConfigContextResp;
import top.continew.admin.orderManage.model.entity.PayOrderDO;

/*
* 小新支付 支付回调接口实现类
*
* @author zhang
* @site https://www.jeequan.com
* @date 2021/9/21 00:16
*/
@Service
@Slf4j
public class DefaultChannelNoticeService extends AbstractChannelNoticeService {



    @Override
    public String getIfCode() {
        return PayConstants.PAY_CODE.DEFAULT;
    }

    @Override
    public MutablePair<String, Object> parseParams(HttpServletRequest request, String payOrderNo, NoticeTypeEnum noticeTypeEnum) {

        //todo:解析请求数据 返回JSON格式。

        try {
            JSONObject params = RequestKits.parseRequest(request);
            String payOrderId = params.getString("mchOrderNo");//请求中上游返回的客户号就是我这边的订单号
            return MutablePair.of(payOrderId, params);

        } catch (Exception e) {
            log.error("error", e);
            throw ResponseException.buildText("ERROR");
        }
    }



    @Override
    //todo: 准备更新此回调方法
    public ChannelRetMsg doNotice(HttpServletRequest request, Object params, PayOrderDO payOrder, MchAppConfigContextResp mchAppConfigContext, NoticeTypeEnum noticeTypeEnum) {
        try {
            //todo:上游私钥签名验证用于验证返回参数的正确性。
            String isvPrivateKey = "";
            // 获取请求参数
            JSONObject jsonParams = (JSONObject) params;
            String checkSign = jsonParams.getString("sign");
            jsonParams.remove("sign");
            // 验证签名
            String regSign = "";
//            String regSign = GoopayKit.getSign(jsonParams, isvParams.getPrivateKey());
            if(!checkSign.equals(regSign)) {
                throw ResponseException.buildText("ERROR");
            }

            //验签成功后判断上游订单状态
            ResponseEntity okResponse = textResp("SUCCESS");

            // 支付状态,0-订单生成,1-支付中,2-支付成功,3-业务处理完成
            String status = jsonParams.getString("status");

            ChannelRetMsg result = new ChannelRetMsg();
            result.setChannelOrderId(jsonParams.getString("orderId")); //渠道订单号
            result.setResponseEntity(okResponse); //响应数据

            result.setChannelState(ChannelRetMsg.ChannelState.WAITING); // 默认支付中

            if("2".equals(status) || "3".equals(status)){
                result.setChannelState(ChannelRetMsg.ChannelState.CONFIRM_SUCCESS);
            }
            return result;
        } catch (Exception e) {
            log.error("error", e);
            throw ResponseException.buildText("ERROR");
        }
    }

}
