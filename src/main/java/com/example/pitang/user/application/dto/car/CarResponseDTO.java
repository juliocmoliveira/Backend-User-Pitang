package com.example.pitang.user.application.dto.car;

import jakarta.validation.constraints.NotBlank;

public record CarResponseDTO(

        @NotBlank
        Integer year,

        @NotBlank
        String licensePlate,

        @NotBlank
        String model,

        @NotBlank
        String color
) { }
