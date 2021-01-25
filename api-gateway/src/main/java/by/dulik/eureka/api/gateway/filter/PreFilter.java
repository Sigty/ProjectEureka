package by.dulik.eureka.api.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
public class PreFilter implements GlobalFilter {

    final Logger logger = LoggerFactory.getLogger(PreFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        logger.info("First Pre-Filter is executed");
        ServerHttpRequest request = exchange.getRequest();
        String requestPath = request.getPath().toString();
        logger.info(String.format("Path : %s", requestPath));
        HttpHeaders headers = exchange.getRequest().getHeaders();
        Set<String> keySetHeaders = headers.keySet();

        keySetHeaders.forEach(headerName -> {
            String headersFirst = headers.getFirst(headerName);
            logger.info(String.format("%s %s", headerName, headersFirst));
        });

        return chain.filter(exchange);
    }

}
