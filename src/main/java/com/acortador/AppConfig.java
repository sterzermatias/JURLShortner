package com.acortador;

import com.acortador.domain.entities.Url;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RedisTemplate<String, Url> redisTemplateUrl(RedisConnectionFactory factory) {
        RedisTemplate<String, Url> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return template;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplateString(RedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return template;
    }


}
