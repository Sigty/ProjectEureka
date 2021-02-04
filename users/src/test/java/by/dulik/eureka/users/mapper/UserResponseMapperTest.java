package by.dulik.eureka.users.mapper;

import by.dulik.eureka.users.dto.UserDto;
import by.dulik.eureka.users.model.response.CreateUserResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@ActiveProfiles("test")
class UserResponseMapperTest {

    @Autowired
    private UserResponseMapper userResponseMapper;

    @Test
    void userDtoToCreateUserDtoTest() {
        UserDto userDto = UserDto.builder()
                .userId("111")
                .firstName("fn")
                .lastName("ln")
                .email("e")
                .password("pass")
                .encryptedPassword("passEnc")
                .build();
        CreateUserResponseDto responseDto = userResponseMapper.userDtoToCreateResponseUserDto(userDto);
        assertNotNull(responseDto);
        assertEquals(userDto.getFirstName(), responseDto.getFirstName());
    }

    @Test
    void createUserRequestDtoTOUserDto(){
        CreateUserResponseDto createUserResponseDto = CreateUserResponseDto.builder()
                .userId("111")
                .firstName("fn")
                .lastName("ln")
                .email("e")
                .build();
        UserDto userDto = userResponseMapper.createUserResponseDtoTOUserDto(createUserResponseDto);
        assertNotNull(userDto);
        assertNotNull(userDto.getUserId());
        assertNull(userDto.getEncryptedPassword());
        assertEquals(userDto.getFirstName(), createUserResponseDto.getFirstName());
    }
}