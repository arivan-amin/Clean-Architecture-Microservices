package io.github.arivanamin.cam.backend.base.application.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper () {
        return new ModelMapper();
    }
}
