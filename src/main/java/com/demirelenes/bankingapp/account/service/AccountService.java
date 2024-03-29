package com.demirelenes.bankingapp.account.service;

import com.demirelenes.bankingapp.account.entity.Account;
import com.demirelenes.bankingapp.account.repository.AccountRepository;
import com.demirelenes.bankingapp.customer.entity.Customer;
import com.demirelenes.bankingapp.customer.service.ICustomerService;
import com.demirelenes.bankingapp.exception.NotFoundException;
import com.demirelenes.bankingapp.transaction.entity.Transaction;
import com.demirelenes.bankingapp.transaction.entity.Transfer;
import com.demirelenes.bankingapp.transaction.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final ICustomerService customerService;
    private final TransferRepository transferRepository;

    public AccountService(AccountRepository accountRepository, ICustomerService customerService, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.transferRepository = transferRepository;
    }

    @Override
    @Transactional
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
        return accountRepository.findById(id).orElseThrow(() -> new NotFoundException("Account with ID = " + id + " not found!"));
    }

    @Override
    public List<Transaction> getTransactionsOfAccount(Long id) {
        List<Transfer> transfersAsDestinationAccount = transferRepository.findByDestinationAccount_Id(id);
        List<Transaction> transactions = getAccountById(id).getTransactions();
        transactions.addAll(transfersAsDestinationAccount);
        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::getDateTime))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY, timeout = 10)
    public Account updateBalanceOfAccount(Long id, BigDecimal amount) {
        Account account = getAccountById(id);
        BigDecimal newBalance = account.getBalance().add(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Balance cannot be negative, " +
                "Balance = " + account.getBalance() + "Update amount = " + amount);
        account.setBalance(newBalance);
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }
}
