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

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import top.continew.admin.common.util.request.RequestKits;


/*
* 实现回调接口抽象类
*
* @author terrfly
* @site https://www.jeequan.com
* @date 2021/6/8 17:18
*/
public abstract class AbstractChannelNoticeService implements IChannelNoticeService {

    @Autowired
    private RequestKits requestKits;

    @Override
    public ResponseEntity doNotifyOrderNotExists(HttpServletRequest request) {
        return textResp("order not exists");
    }

    @Override
    public ResponseEntity doNotifyOrderStateUpdateFail(HttpServletRequest request) {
        return textResp("update status error");
    }

    @Override
    public ResponseEntity doNotifyOrderAlreadyCallBack(HttpServletRequest request) {
        return textResp("ERROR: Order has been already callback. Please do not repeat the callback.");
    }

    @Override
    public ResponseEntity doNotifyOrderAlreadyProcessing(HttpServletRequest request){
        return textResp("ERROR: Order has been processing. Please do not repeat the callback.");
    }
    /** 文本类型的响应数据 **/
    protected ResponseEntity textResp(String text){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.TEXT_HTML);
        return new ResponseEntity(text, httpHeaders, HttpStatus.OK);
    }

    /** json类型的响应数据 **/
    protected ResponseEntity jsonResp(Object body){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(body, httpHeaders, HttpStatus.OK);
    }

    protected JSONObject getReqParamJSON() {
        return requestKits.getReqParamJSON();
    }

}
