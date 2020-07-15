package cn.honghu.blog.im.netty.websocket;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.EventExecutorGroup;
import java.util.concurrent.ThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyWebSocketConfig {

    private static int executorGroupSize = Integer
            .getInteger("netty.server.executor.group.size", 16);
    private int bossGroupSize = Integer.getInteger("netty.server.boss.group.size", 1);
    private int workerGroupSize = Integer.getInteger("netty.server.worker.group.size", 4);

    @Bean(name = "wsBossGroup")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(bossGroupSize,
                new DefaultThreadFactory("ws-boss-thread", true));
    }

    @Bean(name = "wsWorkGroup")
    public NioEventLoopGroup workGroup() {
        return new NioEventLoopGroup(workerGroupSize,
                new DefaultThreadFactory("ws-worker-thread", true));
    }

    @Bean(name = "wsExecutorGroup")
    public EventExecutorGroup executorGroup() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ws-server-thread")
                .build();
        return new DefaultEventExecutorGroup(executorGroupSize, threadFactory);
    }

    @Bean(name = "wsChannelGroup")
    public EventExecutorGroup channelGroup() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ws-channel-Group")
                .build();
        return new DefaultEventExecutorGroup(executorGroupSize, threadFactory);
    }

}
