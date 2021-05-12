package com.github.owgrant24.springbootone.config;

import com.github.owgrant24.springbootone.service.UserService;
import com.github.owgrant24.springbootone.util.UserValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {
    @Bean
    public UserValidator userValidator(){
        return new UserValidator();
    }

    @Bean
    public UserService userService(){
        return mock(UserService.class);
    }
}
