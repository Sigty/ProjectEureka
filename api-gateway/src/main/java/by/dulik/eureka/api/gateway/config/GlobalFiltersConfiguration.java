package by.dulik.eureka.api.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalFiltersConfiguration {

    final Logger logger = LoggerFactory.getLogger(GlobalFiltersConfiguration.class);

    @Order(1)
    @Bean
    public GlobalFilter getSecondPreFilter() {
        return ((exchange, chain) -> {
            logger.info("Second Pre-Filter is executed ... ");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> logger.info("Second Post-Filter is executed ...")));
        });
    }

    @Order(2)
    @Bean
    public GlobalFilter getThirdPreFilter() {
        return ((exchange, chain) -> {
            logger.info("Last Pre-Filter is executed ... ");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> logger.info("Last Post-Filter is executed ...")));
        });
    }
}
