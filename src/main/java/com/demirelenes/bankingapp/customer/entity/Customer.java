package com.demirelenes.bankingapp.customer.entity;

import com.demirelenes.bankingapp.account.entity.Account;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Account> accounts;
}
