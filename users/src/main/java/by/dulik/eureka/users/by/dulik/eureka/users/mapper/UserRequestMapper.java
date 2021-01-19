package by.dulik.eureka.users.by.dulik.eureka.users.mapper;

import by.dulik.eureka.users.by.dulik.eureka.users.dto.UserDto;
import by.dulik.eureka.users.by.dulik.eureka.users.mapper.util.EncodePassword;
import by.dulik.eureka.users.by.dulik.eureka.users.mapper.util.PasswordEncoderMapper;
import by.dulik.eureka.users.by.dulik.eureka.users.model.CreateUserRequestDto;
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
public interface UserRequestMapper {

    @Mappings({
            @Mapping(target = "firstName", source = "userDto.firstName"),
            @Mapping(target = "lastName", source = "userDto.lastName"),
            @Mapping(target = "password", source = "userDto.password"),
            @Mapping(target = "email", source = "userDto.email")
    })
    CreateUserRequestDto userDtoToCreateUserDto(UserDto userDto);

    @Mappings({

            @Mapping(target = "firstName", source = "createUserRequestDto.firstName"),
            @Mapping(target = "lastName", source = "createUserRequestDto.lastName"),
            @Mapping(target = "password", source = "createUserRequestDto.password"),
            @Mapping(target = "email", source = "createUserRequestDto.email"),
            @Mapping(target = "userId", expression = "java(UUID.randomUUID().toString())"),
            @Mapping(target = "encryptedPassword", source = "createUserRequestDto.password", qualifiedBy = EncodePassword.class)
    })
    UserDto createUserRequestDtoTOUserDto(CreateUserRequestDto createUserRequestDto);
}
