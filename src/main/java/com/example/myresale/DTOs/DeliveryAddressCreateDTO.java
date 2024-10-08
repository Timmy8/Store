package com.example.myresale.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressCreateDTO {
    @Size(min = 5, max = 20, message = "Full name must be 5-20 symbols!")
    @NotBlank(message = "Full name can't be blank!")
    private String fullName;

    @Size(min = 3, max = 20, message = "City must be 5-20 symbols!")
    @NotBlank(message = "City can't be blank!")
    private String city;

    @Size(min = 5, max = 20, message = "Street must be 3-20 symbols!")
    @NotBlank(message = "Street can't be blank!")
    private String street;

    @Size(max = 10, message = "House number must be maximum 10 symbols!")
    @NotBlank(message = "House number can't be blank!")
    private String houseNumber;

    @Size(min = 5, max = 6, message = "Zip must be 5 symbols!")
    @NotBlank(message = "Zip can't be blank!")
    @Pattern(regexp = "^[0-9]{2}[-]{0,1}[0-9]{3}$", message = "Zip code template '12345' or '12-345'")
    private String zip;

    @Size(min = 10, max = 15, message = "Phone number must be 10-15 symbols!")
    @NotBlank(message = "Phone number can't be blank!")
    private String phoneNumber;
}
