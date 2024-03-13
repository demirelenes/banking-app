package com.demirelenes.bankingapp.transaction.repository;

import com.demirelenes.bankingapp.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
