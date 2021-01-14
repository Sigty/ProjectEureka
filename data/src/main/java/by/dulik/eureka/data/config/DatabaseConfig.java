package by.dulik.eureka.data.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-data.properties")
@ComponentScan("by.dulik.eureka.data")
public class DatabaseConfig {
}
