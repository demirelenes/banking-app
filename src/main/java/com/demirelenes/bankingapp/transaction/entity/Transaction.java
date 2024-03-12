package com.demirelenes.bankingapp.transaction.entity;

import com.demirelenes.bankingapp.account.entity.Account;
import com.demirelenes.bankingapp.transaction.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION")
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Transaction {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private TransactionType type;

    @Column(name = "DATE_TIME", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "SOURCE_ACCOUNT_ID", referencedColumnName = "ID", nullable = false)
    private Account sourceAccount;
}
