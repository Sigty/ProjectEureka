package by.dulik.eureka.users.by.dulik.eureka.users.mapper;

import by.dulik.eureka.users.by.dulik.eureka.users.dto.UserDto;
import by.dulik.eureka.users.by.dulik.eureka.users.model.CreateUserRequestDto;
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
public interface UserRequestMapper {

    @Mappings({
            @Mapping(target="firstName", source="userDto.firstName"),
            @Mapping(target="lastName", source="userDto.lastName"),
            @Mapping(target="password", source="userDto.password"),
            @Mapping(target="email", source="userDto.email")
    })
    CreateUserRequestDto userDtoToCreateUserDto(UserDto userDto);

    @Mappings({
            @Mapping(target="firstName", source="createUserRequestDto.firstName"),
            @Mapping(target="lastName", source="createUserRequestDto.lastName"),
            @Mapping(target="password", source="createUserRequestDto.password"),
            @Mapping(target="email", source="createUserRequestDto.email"),
            @Mapping(target = "userId", defaultExpression = "java(java.util.UUID.randomUUID().toString())")
    })
    UserDto createUserRequestDtoTOUserDto(CreateUserRequestDto createUserRequestDto);
}
