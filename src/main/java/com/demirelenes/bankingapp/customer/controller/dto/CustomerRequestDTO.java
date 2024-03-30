package com.demirelenes.bankingapp.customer.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRequestDTO {

    @NotBlank(message = "First Name cannot be blank")
    @Pattern(regexp = "[a-zA-Z]]", message = "First Name must contain only letters")
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank(message = "Last Name cannot be blank")
    @Pattern(regexp = "[a-zA-Z]]", message = "Last Name must contain only letters")
    @Size(min = 2, max = 50)
    private String lastName;
}
