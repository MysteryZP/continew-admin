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
package top.continew.admin.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.continew.admin.common.enums.OrderStatusEnum;
import top.continew.admin.orderManage.model.entity.PayOrderDO;
import top.continew.admin.orderManage.model.resp.PayOrderDetailResp;
import top.continew.admin.orderManage.service.PayOrderService;
import top.continew.admin.service.PayOrderProcessService;

/***
* 订单处理通用逻辑
*
* @author terrfly
* @site https://www.jeequan.com
* @date 2021/8/22 16:50
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class PayOrderProcessServiceImpl implements PayOrderProcessService {


    private final PayOrderService orderService;

    /** 明确成功的处理逻辑（除更新订单其他业务） **/
    public void confirmSuccess(PayOrderDO payOrder){

        PayOrderDetailResp orderDetailResp = orderService.get(payOrder.getId());

        //设置订单状态
        payOrder.setStatus(OrderStatusEnum.SUCCESS.getValue());

        //todo:未来补充自动分账功能，不影响主订单任务
//        this.updatePayOrderAutoDivision(payOrder);
        //todo:发送商户通知后面再补充
//        payMchNotifyService.payOrderNotify(payOrder);

    }

}
