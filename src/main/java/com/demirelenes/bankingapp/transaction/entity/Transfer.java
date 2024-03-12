package com.demirelenes.bankingapp.transaction.entity;

import com.demirelenes.bankingapp.account.entity.Account;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Table(name = "TRANSFER")
@Data
@EqualsAndHashCode(callSuper = true)
public class Transfer extends Transaction {

    @Column(name = "DESTINATION_AMOUNT", nullable = false)
    private BigDecimal destinationAmount;

    @ManyToOne
    @JoinColumn(name = "DESTINATION_ACCOUNT_ID", referencedColumnName = "ID", nullable = false)
    private Account destinationAccount;
}
