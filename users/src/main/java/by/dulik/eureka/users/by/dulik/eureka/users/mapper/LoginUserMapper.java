package by.dulik.eureka.users.by.dulik.eureka.users.mapper;

import by.dulik.eureka.users.by.dulik.eureka.users.dto.UserDto;
import by.dulik.eureka.users.by.dulik.eureka.users.model.LoginUserRequestDto;
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
public interface LoginUserMapper {

    @Mappings({
            @Mapping(target = "email", source = "userDto.email"),
            @Mapping(target = "password", source = "userDto.password")
    })
    LoginUserRequestDto userDtoToLoginUserRequestDto(UserDto userDto);

    @Mappings({
            @Mapping(target = "email", source = "loginUserRequestDto.email"),
            @Mapping(target = "password", source = "loginUserRequestDto.password")
    })
    UserDto loginUserRequestDtoToUserDto(LoginUserRequestDto loginUserRequestDto);
}
