package by.dulik.eureka.users.by.dulik.eureka.users.service;

import by.dulik.eureka.data.repository.UserRepository;
import by.dulik.eureka.users.by.dulik.eureka.users.dto.UserDto;
import by.dulik.eureka.users.by.dulik.eureka.users.mapper.UserMapper;
import by.dulik.eureka.users.by.dulik.eureka.users.mapper.UserResponseMapper;
import by.dulik.eureka.users.by.dulik.eureka.users.model.response.MessageResponseDto;
import by.dulik.eureka.users.by.dulik.eureka.users.model.response.UserWithMessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
@Service
public class UsersService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserResponseMapper userResponseMapper;
    private final RestTemplate restTemplate;

    @Transactional
    public UserDto createUser(UserDto newUser) {
        userRepository.save(userMapper.userDtoToUser(newUser));
        return getUserDtoByEmail(newUser.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .map(dto -> User.builder()
                        .username(dto.getEmail())
                        .password(dto.getEncryptedPassword())
                        .disabled(false)
                        .accountExpired(false)
                        .credentialsExpired(false)
                        .accountLocked(false)
                        .authorities(new ArrayList<>())
                        .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Not found user with e-mail : %s", username)));
    }

    public UserDto getUserDetailsByEmail(String email) {
        return getUserDtoByEmail(email);
    }

    private UserDto getUserDtoByEmail(String email) {
        return userMapper
                .userToUserDto(userRepository.findByEmail(email)
                        .orElseThrow(() -> new EntityNotFoundException(String.format("Not found user with e-mail : %s", email))));
    }

    public UserWithMessageResponseDto getUserDtoByUserId(String userId) {

        UserDto userDto = userMapper
                .userToUserDto(userRepository.findByUserId(userId)
                        .orElseThrow(() -> new EntityNotFoundException(String.format("Not found user with userId : %s", userId))));

        String albumsUrl = String.format("http://info-service/v1/users/%s/messages", userId);
        ResponseEntity<List<MessageResponseDto>> albumsListResponse =
                restTemplate.exchange(albumsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<MessageResponseDto>>() {
                });
        List<MessageResponseDto> albumsList = albumsListResponse.getBody();

        UserWithMessageResponseDto responseDto = userResponseMapper.userDtoToUserWithMessageResponseDto(userDto);
        responseDto.setMessages(albumsList);
        return responseDto;
    }
}
