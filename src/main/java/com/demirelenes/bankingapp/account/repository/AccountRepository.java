package com.demirelenes.bankingapp.account.repository;

import com.demirelenes.bankingapp.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
