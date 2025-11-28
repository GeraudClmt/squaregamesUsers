package com.squaregamesusers.usersquare.controller;

import java.util.UUID;

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
import com.squaregamesusers.usersquare.request.CreateUserParamsRequest;
import com.squaregamesusers.usersquare.service.UserServiceInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private UserServiceInterface loginService;

    public UserController(UserServiceInterface loginServiceInterface) {
        this.loginService = loginServiceInterface;
    }

    @Operation(summary = "Create user", description = "Create a with a name", tags = { "Users", "Creation" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User created successfully or error message", content = {
                    @Content(mediaType = "application/json", schema = @Schema(oneOf = { UserDto.class, String.class }))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserParamsRequest request) {
        if (request.getName() == null) {
            return ResponseEntity.ok("Erreur: nom vide");
        }
        UserDto userDto = loginService.createUser(request.getName());

        if (userDto.getUuid() == null) {
            return ResponseEntity.ok("Erreur lors de la création d'un utilisateur");
        }

        return ResponseEntity.ok(userDto);

    }

    @Operation(summary = "Get user by name", description = "Retrieve a user by their name", tags = { "Users",
            "Retrieval" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found or error message", content = {
                    @Content(mediaType = "application/json", schema = @Schema(oneOf = { UserDto.class, String.class }))
            }),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<?> getUserByUuid(@PathVariable UUID uuid) {
        UserDto userDto = loginService.findByUuid(uuid);

        if (userDto == null) {
            return ResponseEntity.ok("UUID introuvable");
        }

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getUserByUuid(@PathVariable String name) {
        UserDto userDto = loginService.getUserByName(name);

        if (userDto == null) {
            return ResponseEntity.ok("Name introuvable");
        }

        return ResponseEntity.ok(userDto);
    }



    @Operation(summary = "Delete a user by UUID", description = "Deletes a user identified by their UUID", tags = {
            "Users", "Deletion" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Number of users deleted", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "1 supprimé"))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @DeleteMapping("/{userUuid}")
    public ResponseEntity<String> deleteUserByUuid(@PathVariable UUID userUuid) {
        Integer nbUserDeleted = loginService.deleteUserByUuid(userUuid);
        return ResponseEntity.ok(nbUserDeleted + " utilisateur supprimé");
    }

}
