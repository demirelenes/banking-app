package com.demirelenes.bankingapp.transaction.repository;

import com.demirelenes.bankingapp.transaction.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    List<Transfer> findByDestinationAccount_Id(Long id);
}
