package com.demirelenes.bankingapp.account.controller;

import com.demirelenes.bankingapp.account.controller.dto.*;
import com.demirelenes.bankingapp.account.entity.Account;
import com.demirelenes.bankingapp.account.service.IAccountService;
import com.demirelenes.bankingapp.transaction.controller.dto.TransactionResponseDTO;
import com.demirelenes.bankingapp.transaction.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final IAccountService accountService;
    private final ModelMapper mapper;

    public AccountController(IAccountService accountService, ModelMapper mapper) {
        this.accountService = accountService;
        this.mapper = mapper;
    }

    @PostMapping
    public AccountResponseDTO createAccount(@RequestBody AccountRequestDTO newAccount) {
        Account accountEntity = new Account();
        accountEntity.setType(newAccount.getType());
        Account createdAccount = accountService.createAccount(accountEntity, newAccount.getCustomerId());
        return mapper.map(createdAccount, AccountResponseDTO.class);
    }

    @GetMapping
    public List<AccountResponseDTO> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return accounts.stream()
                .map(account -> mapper.map(account, AccountResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AccountResponseDTO getAccountById(@PathVariable("id") Long id) {
        Account account = accountService.getAccountById(id);
        return mapper.map(account, AccountResponseDTO.class);
    }

    @GetMapping("/{id}/transactions")
    public List<TransactionResponseDTO> getTransactionsOfAccount(@PathVariable("id") Long id) {
        List<Transaction> transactions = accountService.getTransactionsOfAccount(id);
        return transactions.stream()
                .map(transaction -> mapper.map(transaction, TransactionResponseDTO.class))
                .collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    public AccountResponseDTO updateBalanceOfAccount(@PathVariable("id") Long id, @RequestBody BalanceDTO update) {
        Account updatedAccount = accountService.updateBalanceOfAccountById(id, update.getAmount());
        return mapper.map(updatedAccount, AccountResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deleteAccountById(@PathVariable("id") Long id) {
        accountService.deleteAccountById(id);
    }
}
