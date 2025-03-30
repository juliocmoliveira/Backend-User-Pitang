package com.example.pitang.user.adapters.controller;

import com.example.pitang.user.application.dto.user.UserRequestPostDTO;
import com.example.pitang.user.application.dto.user.UserResponseDTO;
import com.example.pitang.user.application.usecase.CreateUserInteractor;
import com.example.pitang.user.application.usecase.GetUserInteractor;
import com.example.pitang.user.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Operations relationship from users")
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final CreateUserInteractor createUserInteractor;
    private final GetUserInteractor getUserInteractor;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return ResponseEntity.ok(this.getUserInteractor.get());
    }

    @Operation(
            summary = "Create a new user.",
            description = "Create a new user from the data passed in the body."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success, user created"),
            @ApiResponse(responseCode = "400", description = "Solicitation error"),
            @ApiResponse(responseCode = "409", description = "Conflict"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping
    public void createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody @Valid
            UserRequestPostDTO userRequestPostDTO
    ) {
        createUserInteractor.createUser(
                new User(userRequestPostDTO.firstName(),
                        userRequestPostDTO.lastName(),
                        userRequestPostDTO.email(),
                        userRequestPostDTO.birthday(),
                        userRequestPostDTO.login(),
                        userRequestPostDTO.password(),
                        userRequestPostDTO.phone(),
                        userRequestPostDTO.cars()
                )
        );
    }
}
