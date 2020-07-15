package cn.honghu.blog.common.configs.redis;

import cn.honghu.blog.common.web.redis.UserRedisProperties;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Slf4j
@Configuration
@DependsOn(value = "userRedisProperties")
public class RedisConfig {

    @Resource(name = "userRedisProperties")
    UserRedisProperties userRedisProperties;

    public JedisPool getUserRedisPool() {
        JedisPoolConfig config = getUnifiedJedisPoolConfig(userRedisProperties);
        JedisPool pool = new JedisPool(config, userRedisProperties.getHost(),
                userRedisProperties.getPort(),
                (int) userRedisProperties.getTimeout().getSeconds());
        log.info("redis 注入 [{}:{}] ", userRedisProperties.getHost(), userRedisProperties.getPort());
        return pool;
    }

    private JedisPoolConfig getUnifiedJedisPoolConfig(RedisProperties properties) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(properties.getJedis().getPool().getMaxIdle());
        config.setMaxTotal(properties.getJedis().getPool().getMaxActive());
        config.setMaxWaitMillis(properties.getJedis().getPool().getMaxWait().toMillis());
        config.setTestOnBorrow(true);
        config.setTestWhileIdle(true);
        config.setTestOnReturn(true);
        config.setNumTestsPerEvictionRun(10);
        config.setTimeBetweenEvictionRunsMillis(100);
        return config;
    }
}
