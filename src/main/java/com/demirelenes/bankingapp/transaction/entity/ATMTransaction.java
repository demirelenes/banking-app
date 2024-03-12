package com.demirelenes.bankingapp.transaction.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "ATM_TRANSACTION")
@Data
@EqualsAndHashCode(callSuper = true)
public class ATMTransaction extends Transaction {

    @Column(name = "IS_DEPOSIT", nullable = false)
    private Boolean isDeposit;
}
