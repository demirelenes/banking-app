package com.demirelenes.bankingapp.account.service;

import com.demirelenes.bankingapp.account.entity.Account;
import com.demirelenes.bankingapp.transaction.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountService {

    Account createAccount(Account account, Long customerId);

    List<Account> getAllAccounts();

    Account getAccountById(Long id);

    List<Transaction> getTransactionsOfAccount(Long id);

    Account updateBalanceOfAccount(Long id, BigDecimal amount);

    void deleteAccountById(Long id);
}
