package by.dulik.eureka.users.by.dulik.eureka.users.mapper;

import by.dulik.eureka.data.entity.User;
import by.dulik.eureka.users.by.dulik.eureka.users.dto.UserDto;
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
public interface UserMapper {

    @Mappings({
            @Mapping(target = "userId", source = "user.userId"),
            @Mapping(target = "firstName", source = "user.firstName"),
            @Mapping(target = "lastName", source = "user.lastName"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "encryptedPassword", source = "user.encryptedPassword")
    })
    UserDto userToUserDto(User user);

    @Mappings({
            @Mapping(target = "userId", source = "userDto.userId"),
            @Mapping(target = "firstName", source = "userDto.firstName"),
            @Mapping(target = "lastName", source = "userDto.lastName"),
            @Mapping(target = "email", source = "userDto.email"),
            @Mapping(target = "encryptedPassword", source = "userDto.encryptedPassword")
    })
    User userDtoToUser(UserDto userDto);
}
