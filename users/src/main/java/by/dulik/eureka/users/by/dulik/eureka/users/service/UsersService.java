package by.dulik.eureka.users.by.dulik.eureka.users.service;

import by.dulik.eureka.data.repository.UserRepository;
import by.dulik.eureka.users.by.dulik.eureka.users.dto.UserDto;
import by.dulik.eureka.users.by.dulik.eureka.users.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
@Service
public class UsersService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserDto createUser(UserDto newUser) {
        userRepository.save(userMapper.userDtoToUser(newUser));
        return userMapper
                .userToUserDto(userRepository.findByEmail(newUser.getEmail())
                        .orElseThrow(() -> new EntityNotFoundException("Not found user with e-mail : " + newUser.getEmail())));
    }
}
