package com.demirelenes.bankingapp.customer.controller.dto;

import com.demirelenes.bankingapp.account.controller.dto.AccountResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class CustomerResponseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private List<AccountResponseDTO> accounts;
}
