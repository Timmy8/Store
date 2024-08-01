package com.example.myresale.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDeleteRequestDTO {
    @NotBlank(message = "Deleting id can't be blank!")
    @PositiveOrZero(message = "Deleting id must be number!")
    private Long id;

    @NotBlank(message = "Deleting reason can't be blank!")
    private String reason;
}
