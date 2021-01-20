package by.dulik.eureka.users.by.dulik.eureka.users.mapper;

import by.dulik.eureka.users.by.dulik.eureka.users.dto.UserDto;
import by.dulik.eureka.users.by.dulik.eureka.users.mapper.util.PasswordEncoderMapper;
import by.dulik.eureka.users.by.dulik.eureka.users.model.response.CreateUserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        imports = UUID.class,
        uses = PasswordEncoderMapper.class
)
public interface UserResponseMapper {

    @Mappings({
            @Mapping(target = "userId", source = "userDto.userId"),
            @Mapping(target = "firstName", source = "userDto.firstName"),
            @Mapping(target = "lastName", source = "userDto.lastName"),
            @Mapping(target = "email", source = "userDto.email")
    })
    CreateUserResponseDto userDtoToCreateResponseUserDto(UserDto userDto);

    @Mappings({
            @Mapping(target = "userId", source = "createUserResponseDto.userId"),
            @Mapping(target = "firstName", source = "createUserResponseDto.firstName"),
            @Mapping(target = "lastName", source = "createUserResponseDto.lastName"),
            @Mapping(target = "email", source = "createUserResponseDto.email")
    })
    UserDto createUserResponseDtoTOUserDto(CreateUserResponseDto createUserResponseDto);
}
