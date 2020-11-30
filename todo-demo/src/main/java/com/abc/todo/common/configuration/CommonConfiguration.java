package com.abc.todo.common.configuration;

import com.anton.edith.autoconfigure.bean.PermissionMetaCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public PermissionMetaCollector postProcessBeans() {
        return new PermissionMetaCollector();
    }

}
