package cn.honghu.blog.im.netty.tcp;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.EventExecutorGroup;
import java.util.concurrent.ThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyTcpConfig {

    private static int executorGroupSize = Integer
            .getInteger("netty.server.executor.group.size", 16);
    private int bossGroupSize = Integer.getInteger("netty.server.boss.group.size", 1);
    private int workerGroupSize = Integer.getInteger("netty.server.worker.group.size", 4);

    @Bean(name = "bossGroup")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(bossGroupSize, new DefaultThreadFactory("boss-thread", true));
    }

    @Bean(name = "workGroup")
    public NioEventLoopGroup workGroup() {
        return new NioEventLoopGroup(workerGroupSize,
                new DefaultThreadFactory("worker-thread", true));
    }

    @Bean(name = "executorGroup")
    public EventExecutorGroup executorGroup() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("server-thread")
                .build();
        return new DefaultEventExecutorGroup(executorGroupSize * 2, threadFactory);
    }

    @Bean(name = "channelGroup")
    public EventExecutorGroup channelGroup() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("channel-Group")
                .build();
        return new DefaultEventExecutorGroup(executorGroupSize, threadFactory);
    }
}
