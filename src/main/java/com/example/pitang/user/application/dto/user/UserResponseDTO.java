package com.example.pitang.user.application.dto.user;

import com.example.pitang.user.application.dto.car.CarResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record UserResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        LocalDate birthday,
        String login,
        String password,
        String phone,
        List<CarResponseDTO> cars
) { }
