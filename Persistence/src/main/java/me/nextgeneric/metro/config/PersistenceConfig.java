package me.nextgeneric.metro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"me.nextgeneric.metro"}, repositoryImplementationPostfix = "Default")
public class PersistenceConfig {

}
