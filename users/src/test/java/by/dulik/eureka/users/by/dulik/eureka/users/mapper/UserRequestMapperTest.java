package by.dulik.eureka.users.by.dulik.eureka.users.mapper;

import by.dulik.eureka.users.by.dulik.eureka.users.dto.UserDto;
import by.dulik.eureka.users.by.dulik.eureka.users.model.CreateUserRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@ActiveProfiles("test")
class UserRequestMapperTest {

    @Autowired
    private UserRequestMapper userRequestMapper;

    @Test
    void userDtoToCreateUserDtoTest() {
        UserDto userDto = UserDto.builder()
                .firstName("fn")
                .lastName("ln")
                .email("e")
                .password("pass")
                .encryptedPassword("passEnc")
                .build();
        CreateUserRequestDto requestDto = userRequestMapper.userDtoToCreateUserRequestDto(userDto);
        assertNotNull(requestDto);
        assertEquals(userDto.getFirstName(), requestDto.getFirstName());
    }

    @Test
    void createUserRequestDtoTOUserDto(){
        CreateUserRequestDto createUserRequestDto = CreateUserRequestDto.builder()
                .firstName("fn")
                .lastName("ln")
                .email("e")
                .password("pass")
                .build();
        UserDto userDto = userRequestMapper.createUserRequestDtoTOUserDto(createUserRequestDto);
        assertNotNull(userDto);
        assertNotNull(userDto.getUserId());
        assertNotNull(userDto.getEncryptedPassword());
        assertEquals(userDto.getFirstName(), createUserRequestDto.getFirstName());
    }
}