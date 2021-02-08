package config;

import by.dulik.eureka.info.aspect.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectConfig {

    @Bean
    public LoggingAspect getLoggingAspect() {
        return new LoggingAspect();
    }
}
