package com.demirelenes.bankingapp.account.service;

import com.demirelenes.bankingapp.account.entity.Account;
import com.demirelenes.bankingapp.account.repository.AccountRepository;
import com.demirelenes.bankingapp.customer.entity.Customer;
import com.demirelenes.bankingapp.customer.service.ICustomerService;
import com.demirelenes.bankingapp.transaction.entity.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    private final ICustomerService customerService;

    public AccountService(AccountRepository accountRepository, ICustomerService customerService) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
    }

    @Override
    public Account createAccount(Account account, Long customerId) {
        account.setBalance(BigDecimal.ZERO);
        Customer customer = customerService.getCustomerById(customerId);
        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).get(); // Exception will be added
    }

    @Override
    public List<Transaction> getTransactionsOfAccount(Long id) {
        return getAccountById(id).getTransactions();
    }

    @Override
    public Account updateBalanceOfAccount(Long id, BigDecimal amount) {
        Account account = getAccountById(id);
        BigDecimal newBalance = account.getBalance().add(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0); // Exception will be added
        account.setBalance(newBalance);
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }
}
