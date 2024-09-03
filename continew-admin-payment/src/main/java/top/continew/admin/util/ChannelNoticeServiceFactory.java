package top.continew.admin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.continew.admin.channel.IChannelNoticeService;
import top.continew.admin.channel.IPaymentService;
import top.continew.admin.merchantManage.model.entity.MerchantPayMethodDO;

import java.util.Map;

@Component
public class ChannelNoticeServiceFactory {

    private final Map<String, IChannelNoticeService> channelNoticeServices;

    @Autowired
    public ChannelNoticeServiceFactory(Map<String, IChannelNoticeService> channelNoticeServices) {
        this.channelNoticeServices = channelNoticeServices;
    }

    public IChannelNoticeService getChannelNoticeService(String payCode) {
        String serviceName = payCode + "ChannelNoticeService";
        IChannelNoticeService paymentService = channelNoticeServices.get(serviceName);
        return paymentService;
    }
}
