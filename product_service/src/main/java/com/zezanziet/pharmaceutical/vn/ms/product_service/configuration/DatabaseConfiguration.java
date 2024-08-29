package com.zezanziet.pharmaceutical.vn.ms.product_service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.zezanziet.pharmaceutical.vn.ms.product_service.repositories")
@EnableTransactionManagement
public class DatabaseConfiguration {
}
