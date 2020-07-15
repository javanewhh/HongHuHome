package cn.honghu.blog.im.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@Configuration
@EnableScheduling
public class ThreadPoolConfig {

    @Bean("taskScheduler")
    public ScheduledExecutorService scheduledExecutorService() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("scheduled-thread-")
                .build();
        ScheduledThreadPoolExecutor taskScheduled = new ScheduledThreadPoolExecutor(4,
                threadFactory);
        taskScheduled.setMaximumPoolSize(8);
        taskScheduled.setKeepAliveTime(60, TimeUnit.SECONDS);
        return taskScheduled;
    }

}