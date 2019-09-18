package io.riguron.metro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"io.riguron.metro"}, repositoryImplementationPostfix = "Default")
public class PersistenceConfig {

}
