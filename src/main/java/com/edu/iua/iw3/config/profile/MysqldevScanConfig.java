package com.edu.iua.iw3.config.profile;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.edu.iua.iw3", 
    excludeFilters= {})
@EntityScan(basePackages ={  "com.edu.iua.iw3.auth", "com.edu.iua.iw3.model", "com.edu.iua.iw3.integration.cli1.model", "com.edu.iua.iw3.integration.cli2.model"})

// @ConditionalOnExpression(value = "'${spring.profiles.active}'=='cli1'")

@Profile("mysqldev")
public class MysqldevScanConfig {
}