package com.demirelenes.bankingapp.account.entity;

import com.demirelenes.bankingapp.currency.model.CurrencyType;
import com.demirelenes.bankingapp.customer.entity.Customer;
import com.demirelenes.bankingapp.transaction.entity.Transaction;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ACCOUNT")
@Data
@ToString(exclude = {"transactions"})
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private CurrencyType type;

    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "sourceAccount")
    private List<Transaction> transactions;
}
