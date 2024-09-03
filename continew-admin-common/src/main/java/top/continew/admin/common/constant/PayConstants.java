package top.continew.admin.common.constant;

/**
 * @author Peng Zhang
 * @description 支付常量
 * @since 2024/8/13 16:07
 */
public class PayConstants {

    //接口类型
    public interface PAY_CODE{

        String DEFAULT = "default";   // 支付宝官方支付
        String ALIPAY = "alipay";   // 支付宝官方支付
        String WXPAY = "wxpay";     // 微信官方支付
        String YSFPAY = "ysfpay";   // 云闪付开放平台
        String XXPAY = "xxpay";     // 小新支付
        String PPPAY = "pppay";     // Paypal 支付
        String PLSPAY = "plspay";     // 计全支付plus
        String GOOPAY = "goopay";     //goopago支付
    }

    //支付方式代码
    public interface PAY_METHOD_CODE{

        // 特殊支付方式
        String DEFAULT = "default"; //  默认方式

    }

    //支付数据包 类型
    public interface PAY_DATA_TYPE {
        String PAY_URL = "payurl";  //跳转链接的方式  redirectUrl
        String FORM = "form";  //表单提交
        String WX_APP = "wxapp";  //微信app参数
        String ALI_APP = "aliapp";  //支付宝app参数
        String YSF_APP = "ysfapp";  //云闪付app参数
        String CODE_URL = "codeUrl";  //二维码URL
        String CODE_IMG_URL = "codeImgUrl";  //二维码图片显示URL
        String NONE = "none";  //无参数
//        String QR_CONTENT = "qrContent";  //二维码实际内容
    }

    //支付方式代码
    public interface PAY_WAY_CODE{

        // 特殊支付方式
        String QR_CASHIER = "QR_CASHIER";
        String AUTO_BAR = "AUTO_BAR"; // 条码聚合支付（自动分类条码类型）

        String ALI_BAR = "ALI_BAR";  //支付宝条码支付
        String ALI_JSAPI = "ALI_JSAPI";  //支付宝服务窗支付
        String ALI_LITE = "ALI_LITE";  //支付宝小程序支付
        String ALI_APP = "ALI_APP";  //支付宝 app支付
        String ALI_PC = "ALI_PC";  //支付宝 电脑网站支付
        String ALI_WAP = "ALI_WAP";  //支付宝 wap支付
        String ALI_QR = "ALI_QR";  //支付宝 二维码付款

        String YSF_BAR = "YSF_BAR";  //云闪付条码支付
        String YSF_JSAPI = "YSF_JSAPI";  //云闪付服务窗支付

        String WX_JSAPI = "WX_JSAPI";  //微信jsapi支付
        String WX_LITE = "WX_LITE";  //微信小程序支付
        String WX_BAR = "WX_BAR";  //微信条码支付
        String WX_H5 = "WX_H5";  //微信H5支付
        String WX_NATIVE = "WX_NATIVE";  //微信扫码支付

        String UP_APP = "UP_APP";     // 银联App支付
        String UP_WAP = "UP_WAP";     // 银联手机网站支付
        String UP_QR = "UP_QR";       // 银联二维码(主扫)
        String UP_BAR = "UP_BAR";     // 银联二维码(被扫)
        String UP_B2B = "UP_B2B";     // 银联企业网银支付
        String UP_PC = "UP_PC";       // 银联网关支付
        String UP_JSAPI = "UP_JSAPI"; // 银联JS支付

        String PP_PC = "PP_PC"; // Paypal 支付

        String HW_FLB_QR = "HW_FLB_QR";//菲律宾海外
        String HW_BX_GOOPAGO_API = "HW_BX_GOOPAGO_API";//goopago巴西
    }


}
