package by.dulik.eureka.users.by.dulik.eureka.users.service;

import by.dulik.eureka.data.repository.UserRepository;
import by.dulik.eureka.users.by.dulik.eureka.users.dto.UserDto;
import by.dulik.eureka.users.by.dulik.eureka.users.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
@Service
public class UsersService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
                .orElseThrow(() -> new UsernameNotFoundException("Not found user with e-mail : " + username));
    }

    public UserDto getUserDetailsByEmail(String email) {
        return getUserDtoByEmail(email);
    }

    private UserDto getUserDtoByEmail(String email) {
        return userMapper
                .userToUserDto(userRepository.findByEmail(email)
                        .orElseThrow(() -> new EntityNotFoundException("Not found user with e-mail : " + email)));
    }
}
