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
package top.continew.admin.merchantManage.service;

import top.continew.admin.orderManage.model.entity.PayOrderDO;

/*
* 商户通知 service
*
* @author terrfly
* @site https://www.jeequan.com
* @date 2021/6/8 17:43
*/
public interface PayMchNotifyService {

    /** 商户通知信息， 只有订单是终态，才会发送通知， 如明确成功和明确失败 **/
    public void payOrderNotify(PayOrderDO dbPayOrder);

    /**
     * 创建响应URL
     */
    public String createNotifyUrl(PayOrderDO payOrder, String appSecret);
}
