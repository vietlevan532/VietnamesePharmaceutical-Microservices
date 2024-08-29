package com.zezanziet.pharmaceutical.vn.ms.product_service.configuration;

import com.zezanziet.pharmaceutical.vn.ms.product_service.aop.logging.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    public LoggingAspect loggingAspect(Environment env) {return new LoggingAspect(env);}
}
