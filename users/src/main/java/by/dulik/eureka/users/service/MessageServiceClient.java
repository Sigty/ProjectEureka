package by.dulik.eureka.users.service;

import by.dulik.eureka.users.model.response.MessageResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "info-service")
public interface MessageServiceClient {

    @CircuitBreaker(name = "messagesFallback", fallbackMethod = "getMessagesFullback")
    @GetMapping("/v1/users/{userId}/messages")
    List<MessageResponseDto> getMessages(@PathVariable String userId);

    default List<MessageResponseDto> getMessagesFullback(String userId, RuntimeException ex) {
        return Collections.emptyList();
    }
}
