package com.squaregamesusers.usersquare.controller;

import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squaregamesusers.usersquare.dto.UserDto;
import com.squaregamesusers.usersquare.exception.DeleteUserException;
import com.squaregamesusers.usersquare.request.CreateUserParamsRequest;
import com.squaregamesusers.usersquare.service.UserServiceInterface;



@RestController
@RequestMapping("/users")
@CrossOrigin(origins="*")
public class UserController {

    private UserServiceInterface loginService;

    public UserController(UserServiceInterface loginServiceInterface){
        this.loginService = loginServiceInterface;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserParamsRequest request) {
        UserDto userDto = loginService.createUser(request.getName());

        if(userDto.getUuid() == null){
            return ResponseEntity.ok("Erreur de création");
        }

        return ResponseEntity.ok(userDto);
        
    }
    
    @GetMapping("/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name) {
        UserDto userDto = loginService.getUserByName(name);
        if(userDto.getUuid() == null){
            return ResponseEntity.ok("Erreur de création");
        }

        return ResponseEntity.ok(userDto);
    }
    
    @DeleteMapping("/{userUuid}")
    public ResponseEntity<String> deleteUserByUuid(@PathVariable UUID userUuid){
        Integer nbUserDeleted = loginService.deleteUserByUuid(userUuid);
        return ResponseEntity.ok(nbUserDeleted + " supprimé");
    }

}
