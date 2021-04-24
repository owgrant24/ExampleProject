package com.github.owgrant24.one.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;

@Configuration
@ComponentScan("com.github.owgrant24.one.aspect")
@EnableAspectJAutoProxy
@Order(3)
public class AOPConfig {
}
