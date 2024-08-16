package top.continew.admin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.continew.admin.channel.IPaymentService;
import top.continew.admin.merchantManage.model.entity.MerchantPayMethodDO;

import java.util.Map;

@Component
public class PaymentServiceFactory {

    private final Map<String, IPaymentService> paymentServices;

    @Autowired
    public PaymentServiceFactory(Map<String, IPaymentService> paymentServices) {
        this.paymentServices = paymentServices;
    }

    public IPaymentService getPaymentService(String payCode,String payMethodCode,MerchantPayMethodDO mchPayPassage) {
        payMethodCode = payMethodCode.substring(0, 1).toUpperCase() + payMethodCode.substring(1);
        payMethodCode = payMethodCode.replaceAll("_","");
        String serviceName = payCode + "PaymentBy"+payMethodCode+"Service";
        IPaymentService paymentService = paymentServices.get(serviceName);
        if (paymentService == null || !paymentService.isSupport(mchPayPassage.getPayMethodCode())) {
            throw new IllegalArgumentException("无此支付通道接口或接口不支持该支付方式");
        }
        return paymentService;
    }
}
