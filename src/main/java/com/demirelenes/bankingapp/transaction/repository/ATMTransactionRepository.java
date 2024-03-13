package com.demirelenes.bankingapp.transaction.repository;

import com.demirelenes.bankingapp.transaction.entity.ATMTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ATMTransactionRepository extends JpaRepository<ATMTransaction, Long> {
}
