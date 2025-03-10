package com.Expense_Management_System.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }

}
