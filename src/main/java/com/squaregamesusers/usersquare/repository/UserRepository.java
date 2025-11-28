package com.squaregamesusers.usersquare.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.squaregamesusers.usersquare.model.UserModel;



public interface UserRepository extends CrudRepository<UserModel, Integer> {
    UserModel findByName(String name);
    Integer deleteByUuid(UUID uuid);
    UserModel findByUuid(UUID uuid);
}
