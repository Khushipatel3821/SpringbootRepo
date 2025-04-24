package com.core.springboot.tutorial.configuration;

import com.core.springboot.tutorial.DB;
import com.core.springboot.tutorial.DevDB;
import com.core.springboot.tutorial.ProdDB;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Dependency Injection
@Configuration
public class AppConfig {

    @Bean
    @ConditionalOnProperty(name = "project.environment", havingValue = "development")
    public DB getDevDBBean() {
        return new DevDB();
    }

    @Bean
    @ConditionalOnProperty(name = "project.environment", havingValue = "production")
    public DB getProdDBBean() {
        return new ProdDB();
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
