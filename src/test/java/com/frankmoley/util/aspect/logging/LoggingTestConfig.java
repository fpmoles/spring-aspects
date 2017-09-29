package com.frankmoley.util.aspect.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class LoggingTestConfig {

    @Bean
    public TestingClass testingClass(){
        return new TestingClass();
    }
}
