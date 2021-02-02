package by.dulik.eureka.users.by.dulik.eureka.users.controller;

import by.dulik.eureka.users.by.dulik.eureka.users.dto.UserDto;
import by.dulik.eureka.users.by.dulik.eureka.users.mapper.UserRequestMapper;
import by.dulik.eureka.users.by.dulik.eureka.users.mapper.UserResponseMapper;
import by.dulik.eureka.users.by.dulik.eureka.users.model.CreateUserRequestDto;
import by.dulik.eureka.users.by.dulik.eureka.users.model.response.CreateUserResponseDto;
import by.dulik.eureka.users.by.dulik.eureka.users.model.response.UserResponseDto;
import by.dulik.eureka.users.by.dulik.eureka.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("v1/users")
@RestController
public class UsersController {

    private final UsersService usersService;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;
    private final Environment environment;

    @GetMapping("/echo")
    public ResponseEntity<Map<String, Object>> echo() {
        return ResponseEntity.ok(Collections.singletonMap("ok", true));
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> status() {
        return ResponseEntity.ok(Map.of("port", environment.getProperty("local.server.port"),
                "testField", environment.getProperty("test.api.props"),
                "token-expiration-time", environment.getProperty("token.expiration.time")));
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseDto> createUser(@Valid @RequestBody CreateUserRequestDto createUser) {
        UserDto newUser = usersService.createUser(userRequestMapper.createUserRequestDtoTOUserDto(createUser));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userResponseMapper.userDtoToCreateResponseUserDto(newUser));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String userId) {
        UserDto userDto = usersService.getUserDtoByUserId(userId);
        CreateUserResponseDto responseDto = userResponseMapper.userDtoToCreateResponseUserDto(userDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }

}
