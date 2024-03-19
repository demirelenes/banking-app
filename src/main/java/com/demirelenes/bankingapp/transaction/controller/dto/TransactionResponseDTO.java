package com.demirelenes.bankingapp.transaction.controller.dto;

import com.demirelenes.bankingapp.transaction.TransactionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionResponseDTO {

    private Long id;

    private TransactionType type;

    private LocalDateTime dateTime;

    private String description;
}
