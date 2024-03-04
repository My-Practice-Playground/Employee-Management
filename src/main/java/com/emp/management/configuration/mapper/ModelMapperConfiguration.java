package com.emp.management.configuration.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    /**
     * MODEL MAPPER BEAN
     * @return
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
