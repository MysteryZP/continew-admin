package top.continew.admin.common.util.lang;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Peng Zhang
 * @description 字符串工具类
 * @since 2024/7/31 15:13
 */
public class StringKits {

    // 自增序列，最大值 9999，超过后从 0 开始
    private static final AtomicInteger sequence = new AtomicInteger(0);

    // 机器标识符，可以是IP后缀或机器ID
    private static final String machineId = "001"; // 示例中的机器ID，可以根据具体情况替换


    /** 是否 http 或 https连接 **/
    public static boolean isAvailableUrl(String url){

        if(StringUtils.isEmpty(url)){
            return false;
        }

        return url.startsWith("http://") ||url.startsWith("https://");
    }

    //
    /** 生成唯一订单号的方法 **/
    public static String generateOrderNumber() {
        // 获取当前时间戳
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

        // 获取并发下的唯一序列号
        int currentSequence = sequence.getAndIncrement();

        // 如果序列号达到最大值，重置为0
        if (currentSequence > 9999) {
            sequence.set(0);
            currentSequence = sequence.getAndIncrement();
        }

        // 将序列号格式化为四位数，不足四位前面补0
        String sequenceStr = String.format("%04d", currentSequence);

        // 组合订单号：时间戳 + 机器标识符 + 四位序列号
        return timeStamp + machineId + sequenceStr;
    }
}
