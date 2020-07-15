package cn.honghu.blog.common.web.support.json;

import cn.honghu.blog.common.web.response.Result;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

@Slf4j
public class FyFastJsonHttpMessageConvert extends FastJsonHttpMessageConverter {

    @Value("${spring.profiles.active}")
    private String activeProfiles;

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        super.writeInternal(object, outputMessage);
        String prod = "prod";
        if (!prod.equals(activeProfiles)) {
            String jsonString = JSONObject
                    .toJSONString(object, super.getFastJsonConfig().getSerializeConfig());
            log.info("http return:{}", jsonString);
        } else {
            if (object instanceof Result) {
                log.info("code:{}", ((Result) object).getCode());
            }
        }
    }

}
