package by.dulik.eureka.info.service;

import by.dulik.eureka.data.repository.MessageRepository;
import by.dulik.eureka.info.mapper.MessageMapper;
import by.dulik.eureka.info.model.MessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public List<MessageResponseDto> getMessageByUserId(String userId){
        return messageRepository.findAllByUserUserId(userId)
                .stream()
                .map(messageMapper::messageToMessageResponseDto)
                .collect(Collectors.toList());
    }
}
