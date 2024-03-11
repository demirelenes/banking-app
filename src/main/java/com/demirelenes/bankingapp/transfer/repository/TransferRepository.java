package com.demirelenes.bankingapp.transfer.repository;

import com.demirelenes.bankingapp.transfer.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
