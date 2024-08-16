package top.continew.admin.channel.defaultPay;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import top.continew.admin.channel.AbstractPaymentService;
import top.continew.admin.common.resp.ChannelResp;
import top.continew.admin.model.req.UnifiedOrderReq;
import top.continew.admin.model.resp.AbstractResp;
import top.continew.admin.model.resp.MchAppConfigContextResp;
import top.continew.admin.orderManage.model.entity.PayOrderDO;

import java.util.Map;

/**
 * 计全支付plus
 *
 * @author yurong
 * @site https://www.jeequan.com
 * @date 2022/8/11 15:37
 */
@Service
public class DefaultPaymentService extends AbstractPaymentService {

    @Override
    public String getPayCode() {
        return "default";
    }

    @Override
    public boolean isSupport(String wayCode) {
        return true;
    }

    @Override
    public String preCheck(UnifiedOrderReq req, PayOrderDO payOrder) {
        System.out.println("preCheck----");
        return "";
    }

    @Override
    public AbstractResp pay(UnifiedOrderReq unifiedOrderReq, PayOrderDO payOrder, MchAppConfigContextResp mchAppConfigContext) {
        return null;
    }

    protected JSONObject doPay(PayOrderDO payOrder, Map paramMap, ChannelResp channelResp, MchAppConfigContextResp mchAppConfigContextResp){

        return null;
    }

}
