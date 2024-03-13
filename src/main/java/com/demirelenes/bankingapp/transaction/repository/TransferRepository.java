package com.demirelenes.bankingapp.transaction.repository;

import com.demirelenes.bankingapp.transaction.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
