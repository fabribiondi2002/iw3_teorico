package com.edu.iua.iw3.config.profile;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//Solo toma los repositorios de cli1, no los de cli2
@EnableJpaRepositories(basePackages = "com.edu.iua.iw3", 
    excludeFilters= @ComponentScan.Filter(type = FilterType.REGEX, pattern="com\\.edu\\.iua\\.iw3\\.integration\\.cli2\\..*"))
//Solo escanea las entidades de cli1, no las de cli2
@EntityScan(basePackages ={ "com.edu.iua.iw3.integration.cli1.model", "com.edu.iua.iw3.auth", "com.edu.iua.iw3.model"})

// @ConditionalOnExpression(value = "'${spring.profiles.active}'=='cli1'")

@Profile("cli1")
public class Cli1ScanConfig {
}