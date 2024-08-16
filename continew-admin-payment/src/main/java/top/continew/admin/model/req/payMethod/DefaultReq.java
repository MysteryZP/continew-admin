package top.continew.admin.model.req.payMethod;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.common.constant.PayConstants;
import top.continew.admin.model.req.UnifiedOrderReq;

@Data
@Schema(description = "默认支付接口")
public class DefaultReq extends UnifiedOrderReq {

    /** 构造函数 **/
    public DefaultReq(){
        this.setPayCode(PayConstants.PAY_METHOD_CODE.DEFAULT);
    }

}
