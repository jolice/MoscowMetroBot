package me.riguron.metro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"me.riguron.metro"}, repositoryImplementationPostfix = "Default")
public class PersistenceConfig {

}
