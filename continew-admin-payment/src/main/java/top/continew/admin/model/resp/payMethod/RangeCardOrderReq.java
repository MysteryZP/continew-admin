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
package top.continew.admin.model.resp.payMethod;

import lombok.Data;
import top.continew.admin.model.req.CommonPayDataReq;

/*
 * 银行卡轮训
 *
 * @author Jhon sam
 * @site https://www.jeequan.com
 * @date 2021/6/8 17:34
 */
@Data
public class RangeCardOrderReq extends CommonPayDataReq {

    /** 构造函数 **/
    public RangeCardOrderReq(){
        this.setPayMethodCode("Range_Card");
    }

}