package com.anton.edith.autoconfigure.configuration;

import com.anton.edith.autoconfigure.interceptor.AuthorizeInterceptor;
import com.anton.edith.autoconfigure.interceptor.LogInterceptor;
import com.anton.edith.core.token.DoubleJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(EdithCmsProperties.class)
public class EdithConfiguration {

    @Autowired
    private EdithCmsProperties properties;

    @Bean
    public DoubleJWT jwter() {
        String secret = properties.getTokenSecret();
        Long accessExpire = properties.getTokenAccessExpire();
        Long refreshExpire = properties.getTokenRefreshExpire();
        if (accessExpire == null) {
            // 一个小时
            accessExpire = 60 * 60L;
        }
        if (refreshExpire == null) {
            // 一个月
            refreshExpire = 60 * 60 * 24 * 30L;
        }
        return new DoubleJWT(secret, accessExpire, refreshExpire);
    }

    @Bean
    public AuthorizeInterceptor authInterceptor() {
        String[] excludeMethods = properties.getExcludeMethods();
        return new AuthorizeInterceptor(excludeMethods);
    }

    @Bean
    @ConditionalOnProperty(prefix = "edith.cms", value = "logger-enabled", havingValue = "true")
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

}
