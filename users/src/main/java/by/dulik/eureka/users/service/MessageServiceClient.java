package by.dulik.eureka.users.service;

import by.dulik.eureka.users.model.response.MessageResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "info-service")
public interface MessageServiceClient {

    @GetMapping("/v1/users/{userId}/messages!")
    List<MessageResponseDto> getMessages(@PathVariable String userId);
}
