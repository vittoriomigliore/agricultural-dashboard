package it.vittoriomigliore.agriculturaldashboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "it.vittoriomigliore.agriculturaldashboard.core.repository")
public class AppConfig {
}
