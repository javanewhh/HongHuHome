package cn.honghu.blog.backstage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"cn.honghu.blog.common", "cn.honghu.blog.backstage"})
@PropertySource(value = "db-${spring.profiles.active}.properties")
@MapperScan(basePackages = "com.leon.chaosale.fy.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
