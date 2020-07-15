package cn.honghu.blog.common.configs.web;

import cn.honghu.blog.common.web.filter.ParamsFilter;
import cn.honghu.blog.common.web.support.json.WebUtil;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        converters.add(fastJsonHttpMessageConverters());
    }

    /**
     * 使用FastJson序列化返回的http消息
     */
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverters() {
        return WebUtil.getFastJsonHttpMessageConverter();
    }

    @Bean
    public ParamsFilter initParamFilter() {
        return new ParamsFilter();
    }

    /**
     * 注入过滤器链
     */
    @Bean
    public FilterRegistrationBean paramFilter() {
        FilterRegistrationBean<ParamsFilter> register = new FilterRegistrationBean<>();
        register.setFilter(initParamFilter());
        register.addUrlPatterns("/*");
        register.setName("ParamFilter");
        register.setOrder(1);
        return register;
    }


}
