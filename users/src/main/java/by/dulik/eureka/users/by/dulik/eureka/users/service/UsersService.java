package by.dulik.eureka.users.by.dulik.eureka.users.service;

import by.dulik.eureka.users.by.dulik.eureka.users.dto.UserDto;

import java.util.UUID;

public class UsersService {

    public UserDto createUser(UserDto newUser) {

        newUser.setUserId(UUID.randomUUID().toString());
        return null;
    }
}
