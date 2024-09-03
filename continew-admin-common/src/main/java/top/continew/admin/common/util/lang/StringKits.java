package top.continew.admin.common.util.lang;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
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

    private static final Random random = new Random();
    private static final int MAX_COUNTER = 5; // 计数器最大值


    /** 是否 http 或 https连接 **/
    public static boolean isAvailableUrl(String url){

        if(StringUtils.isEmpty(url)){
            return false;
        }

        return url.startsWith("http://") ||url.startsWith("https://");
    }

    public static String generateOrderNumber() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = sdf.format(new Date());

        int randomNum = random.nextInt(1000000); // 生成3位随机数

        // 组合时间戳、随机数和计数器，确保订单号长度为18位
        return String.format("%s%03d", timestamp, randomNum);
    }
}
