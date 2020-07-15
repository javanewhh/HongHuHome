package cn.honghu.blog.common.utils;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    /**
     * 得到当前时间的秒数
     */
    public static long nowToSeconds() {
        Instant instant = Instant.now();
        return TimeUnit.MILLISECONDS.toSeconds(instant.toEpochMilli());
    }

    /**
     * 得到当前时间的毫秒数
     */
    public static long nowToMillisecond() {
        Instant instant = Instant.now();
        return instant.toEpochMilli();
    }

}
