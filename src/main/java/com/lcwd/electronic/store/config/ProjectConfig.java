package com.lcwd.electronic.store.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Entity;

@Configuration
public class ProjectConfig {
    @Bean
    public ModelMapper mapper(){
        return  new ModelMapper();
    }
}
