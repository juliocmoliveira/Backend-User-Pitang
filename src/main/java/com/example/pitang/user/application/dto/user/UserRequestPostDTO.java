package com.example.pitang.user.application.dto.user;

import com.example.pitang.user.domain.model.Car;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.List;

public record UserRequestPostDTO(

        @NotBlank(message = "Missing fields")
        @Schema(description = "First name of user", example = "Júlio")
        String firstName,

        @NotBlank(message = "Missing fields")
        @Schema(description = "Last name of user", example = "Oliveira")
        String lastName,

        @NotBlank(message = "Missing fields")
        @Email(message = "Invalid fields")
        @Schema(description = "Email of user", example = "juliocesar_morais@outlook.com")
        String email,

        @NotNull(message = "Missing fields")
        @Past(message = "Invalid fields")
        @Schema(description = "User's date of birthday", example = "1998-08-17T00:00:00.000+0000")
        LocalDate birthday,

        @NotBlank(message = "Missing fields")
        @Schema(description = "User login name", example = "jcmoliveira")
        String login,

        @NotBlank(message = "Missing fields")
        @Schema(description = "User password", example = "123")
        String password,

        @NotBlank(message = "Missing fields")
        @Schema(description = "User phone", example = "+5581988045016")
        String phone,

        @Schema(description = "User car list")
        List<Car>cars
) {
}
