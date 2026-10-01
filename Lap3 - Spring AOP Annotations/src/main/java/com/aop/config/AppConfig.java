package com.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy // tells Spring: scan for @Aspect beans and proxy them
@ComponentScan("com.aop")
public class AppConfig {
}
