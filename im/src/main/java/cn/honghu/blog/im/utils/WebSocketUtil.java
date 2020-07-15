package cn.honghu.blog.im.utils;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.util.Strings;

public class WebSocketUtil {

    public static void main(String[] args) {
        String testStr = "/websocket?sessionId=123";
        Map<String, String> stringStringMap = url2ParamMap(testStr);
        System.out.println(stringStringMap.get("sessionId"));
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String truncateUrlPage(String strURL) {
        String strAllParam = null;

        strURL = strURL.trim();

        String[] arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }

        return strAllParam;
    }

    /**
     * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param url url地址
     * @return url请求参数部分
     */
    public static Map<String, String> url2ParamMap(String url) {
        Map<String, String> mapRequest = new HashMap<>(16);
        String strUrlParam = truncateUrlPage(url);
        if (strUrlParam == null) {
            return mapRequest;
        }
        String[] arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = strSplit.split("[=]");
            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

            } else {
                if (null != arrSplitEqual[0] && !Strings.EMPTY.equals(arrSplitEqual[0])) {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }
}
