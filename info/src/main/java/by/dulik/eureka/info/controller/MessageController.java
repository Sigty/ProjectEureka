package by.dulik.eureka.info.controller;

import by.dulik.eureka.info.model.MessageResponseDto;
import by.dulik.eureka.info.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("v1/users")
@RestController
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{userId}/messages")
    public List<MessageResponseDto> userMessages(@PathVariable String userId) {
        return messageService.getMessageByUserId(userId);
    }
}
