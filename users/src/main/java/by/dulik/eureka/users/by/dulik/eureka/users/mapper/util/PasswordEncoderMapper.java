package by.dulik.eureka.users.by.dulik.eureka.users.mapper.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PasswordEncoderMapper {

    private final PasswordEncoder passwordEncoder;

    @EncodePassword
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}