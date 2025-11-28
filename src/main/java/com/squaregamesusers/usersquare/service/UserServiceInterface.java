package com.squaregamesusers.usersquare.service;

import java.util.UUID;

import com.squaregamesusers.usersquare.dto.UserDto;

public interface  UserServiceInterface {

    UserDto createUser(String name);
    UserDto getUserByName(String name);
    Integer deleteUserByUuid(UUID uuid);
    UserDto findByUuid(UUID uuid);

}
