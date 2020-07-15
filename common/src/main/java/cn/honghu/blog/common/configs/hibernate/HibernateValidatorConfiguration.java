package cn.honghu.blog.common.configs.hibernate;

import javax.validation.Validation;
import javax.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Slf4j
@Configuration
public class HibernateValidatorConfiguration {

    @Bean
    public static Validator validator() {
        log.info("hibernate Validator 注入");
        return Validation
                .byProvider(HibernateValidator.class)
                .configure()
                //快速返回模式，有一个验证失败立即返回错误信息
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }

    /**
     * JSR和Hibernate validator的校验只能对Object的属性进行校验 不能对单个的参数进行校验 spring 在此基础上进行了扩展
     * 添加了MethodValidationPostProcessor拦截器 可以实现对方法参数的校验
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        log.info("methodValidationPostProcessor 注入");
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator());
        return processor;
    }
}
