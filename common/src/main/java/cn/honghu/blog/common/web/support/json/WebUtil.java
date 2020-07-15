package cn.honghu.blog.common.web.support.json;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;

public class WebUtil {


    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

    public static FastJsonHttpMessageConverter getFastJsonHttpMessageConverter() {
        FyFastJsonHttpMessageConvert fastJsonHttpMessageConverter = new FyFastJsonHttpMessageConvert();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                // 防止循环引用
                SerializerFeature.DisableCircularReferenceDetect,
                // 空集合返回[],不返回null
                SerializerFeature.WriteNullListAsEmpty,
                // 空字符串返回"",不返回null
                SerializerFeature.WriteNullStringAsEmpty,
                // MapValue返回"",不返回null
                SerializerFeature.WriteMapNullValue
        );
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);

        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return fastJsonHttpMessageConverter;
    }

    @SuppressWarnings(value = "unchecked")
    public static String getParams(HttpServletRequest request, String method) {
        Map map = getRequestMap(method, request);
        return JSONObject.toJSONString(map);
    }

    private static Map getRequestMap(String method, HttpServletRequest request) {
        if (METHOD_POST.equals(method)) {
            return request.getParameterMap();
        }
        if (METHOD_GET.equals(method)) {
            return getQueryStrings(request);
        }
        return Collections.EMPTY_MAP;
    }

    private static Map<String, String> getQueryStrings(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<String, String>(16);
        String queryString = request.getQueryString();
        if (StrUtil.isNotEmpty(queryString)) {
            String[] paramArray = queryString.split("&");
            if (paramArray.length > 0) {
                for (String paramStr : paramArray) {
                    String[] paramTemp = paramStr.split("=");
                    if (paramTemp.length != 2) {
                        continue;
                    }
                    paramMap.put(paramTemp[0], paramTemp[1]);
                }
            }
        }
        return paramMap;
    }
}
