package by.dulik.eureka.info.mapper;

import by.dulik.eureka.data.entity.Message;
import by.dulik.eureka.info.model.MessageResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface MessageMapper {

    @Mappings({
            @Mapping(target = "messageId", source = "message.id"),
            @Mapping(target = "userId", source = "message.user.userId"),
            @Mapping(target = "title", source = "message.title"),
            @Mapping(target = "text", source = "message.text")
    })
    MessageResponseDto messageToMessageResponseDto(Message message);

    @Mappings({
            @Mapping(target = "id", source = "messageResponseDto.messageId"),
            @Mapping(target = "title", source = "messageResponseDto.title"),
            @Mapping(target = "text", source = "messageResponseDto.text")
    })
    Message messageResponseDtoToMessage(MessageResponseDto messageResponseDto);
}
