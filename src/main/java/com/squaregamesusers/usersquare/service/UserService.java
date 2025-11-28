package com.squaregamesusers.usersquare.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.squaregamesusers.usersquare.dto.UserDto;
import com.squaregamesusers.usersquare.model.UserModel;
import com.squaregamesusers.usersquare.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserServiceInterface{

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(String name) {
        UserModel userModel = new UserModel(name);

        userRepository.save(userModel);

        return new UserDto(userModel.getName(), userModel.getUuid());
    }

    @Override
    public UserDto getUserByName(String name) {
        UserModel userModel = userRepository.findByName(name);
        if(userModel == null){
            return null;
        }
        return new UserDto(userModel.getName(), userModel.getUuid());
    }

    @Transactional
    @Override
    public Integer deleteUserByUuid(UUID uuid) {
        return userRepository.deleteByUuid(uuid);
    
    }

    @Override
    public UserDto findByUuid(UUID uuid){
        UserModel userModel = userRepository.findByUuid(uuid);
        return new UserDto(userModel.getName(), userModel.getUuid());
    }

}
