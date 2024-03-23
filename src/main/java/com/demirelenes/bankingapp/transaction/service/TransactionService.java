package com.demirelenes.bankingapp.transaction.service;

import com.demirelenes.bankingapp.account.entity.Account;
import com.demirelenes.bankingapp.account.service.IAccountService;
import com.demirelenes.bankingapp.currency.service.ICurrencyService;
import com.demirelenes.bankingapp.transaction.TransactionType;
import com.demirelenes.bankingapp.transaction.entity.ATMTransaction;
import com.demirelenes.bankingapp.transaction.entity.Transaction;
import com.demirelenes.bankingapp.transaction.entity.Transfer;
import com.demirelenes.bankingapp.transaction.repository.ATMTransactionRepository;
import com.demirelenes.bankingapp.transaction.repository.TransactionRepository;
import com.demirelenes.bankingapp.transaction.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;
    private final ATMTransactionRepository atmTransactionRepository;
    private final TransferRepository transferRepository;
    private final IAccountService accountService;
    private final ICurrencyService currencyService;

    public TransactionService(TransactionRepository transactionRepository,
                              ATMTransactionRepository atmTransactionRepository,
                              TransferRepository transferRepository,
                              IAccountService accountService,
                              ICurrencyService currencyService) {
        this.transactionRepository = transactionRepository;
        this.atmTransactionRepository = atmTransactionRepository;
        this.transferRepository = transferRepository;
        this.accountService = accountService;
        this.currencyService = currencyService;
    }

    @Override
    public Transaction processAtmTransaction(ATMTransaction atmTransaction, Long accountId) {
        Account account = accountService.getAccountById(accountId);
        atmTransaction.setSourceAccount(account);

        BigDecimal amount = atmTransaction.getAmount();
        if (!atmTransaction.getIsDeposit()) amount = amount.negate();
        accountService.updateBalanceOfAccount(accountId, amount);

        atmTransaction.setType(TransactionType.ATM);
        atmTransaction.setDateTime(LocalDateTime.now());

        String transactionType = atmTransaction.getIsDeposit() ? "deposited to" : "withdrew from";
        atmTransaction.setDescription(String.format("%s %s is %s account with id: %s.", amount, account.getType(), transactionType, account.getId()));

        return atmTransactionRepository.save(atmTransaction);
    }

    @Override
    public Transaction makeTransfer(Transfer transfer, Long sourceId, Long destinationId) throws IOException, ParserConfigurationException, SAXException {
        if (sourceId.equals(destinationId)); // Exception will be thrown

        Account sourceAccount = accountService.getAccountById(sourceId);
        Account destinationAccount = accountService.getAccountById(destinationId);

        BigDecimal destinationAmount = transfer.getAmount();
        if (sourceAccount.getType() != destinationAccount.getType()) {
            BigDecimal exchangeRate = currencyService.getExchangeRate(sourceAccount.getType(), destinationAccount.getType());
            destinationAmount = destinationAmount.multiply(exchangeRate);
        }
        transfer.setDestinationAmount(destinationAmount);

        accountService.updateBalanceOfAccount(sourceAccount.getId(), transfer.getAmount().negate());
        accountService.updateBalanceOfAccount(destinationAccount.getId(), destinationAmount);

        transfer.setSourceAccount(sourceAccount);
        transfer.setDestinationAccount(destinationAccount);
        transfer.setType(TransactionType.Transfer);
        transfer.setDateTime(LocalDateTime.now());

        transfer.setDescription(String.format("%s %s transferred from account with id: %s to %s as %s %s", transfer.getAmount(), sourceAccount.getType(),
                sourceAccount.getId(), destinationAccount.getId(), transfer.getDestinationAmount(), destinationAccount.getType()));

        return transferRepository.save(transfer);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).get(); // Exception will be added
    }
}
