package com.volvo.mall.security.config;

import com.volvo.mall.common.config.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Redis相关配置
 */

// 启用缓存
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
