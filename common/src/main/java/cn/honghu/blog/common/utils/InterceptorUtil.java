package cn.honghu.blog.common.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Function: 拦截工具使用的一些公告方法
 */
@Slf4j
public class InterceptorUtil {

    public static final String REQUEST_ID = "requestId";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

    private static final String SIGN = "sign";
    private static String signKey = "OoSUAlE4Jpv1hHO3iezgB3eCKcz0xp";

    public final static String SESSION_ID = "sessionId";

}
