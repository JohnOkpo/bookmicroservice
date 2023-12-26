package com.stark3ase.ratems.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateConfig
{
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
}
