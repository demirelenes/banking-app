package com.demirelenes.bankingapp.transaction.controller;

import com.demirelenes.bankingapp.transaction.controller.dto.ATMTransactionDTO;
import com.demirelenes.bankingapp.transaction.controller.dto.TransactionResponseDTO;
import com.demirelenes.bankingapp.transaction.controller.dto.TransferDTO;
import com.demirelenes.bankingapp.transaction.entity.ATMTransaction;
import com.demirelenes.bankingapp.transaction.entity.Transaction;
import com.demirelenes.bankingapp.transaction.entity.Transfer;
import com.demirelenes.bankingapp.transaction.service.ITransactionService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final ITransactionService transactionService;
    private final ModelMapper mapper;

    public TransactionController(ITransactionService transactionService, ModelMapper mapper) {
        this.transactionService = transactionService;
        this.mapper = mapper;
    }

    @PostMapping("/atm")
    public TransactionResponseDTO processAtmTransaction(@Valid @RequestBody ATMTransactionDTO newTransaction) {
        var transactionEntity = new ATMTransaction();
        transactionEntity.setIsDeposit(newTransaction.getIsDeposit());
        transactionEntity.setAmount(newTransaction.getAmount());
        Transaction createdTransaction = transactionService.processAtmTransaction(transactionEntity, newTransaction.getSourceAccountId());
        return mapper.map(createdTransaction, TransactionResponseDTO.class);
    }

    @PostMapping("/transfer")
    public TransactionResponseDTO makeTransfer(@Valid @RequestBody TransferDTO newTransfer) throws IOException, ParserConfigurationException, SAXException {
        var transferEntity = new Transfer();
        transferEntity.setAmount(newTransfer.getAmount());
        Transaction createdTransaction = transactionService.makeTransfer(transferEntity, newTransfer.getSourceAccountId(), newTransfer.getDestinationAccountId());
        return mapper.map(createdTransaction, TransactionResponseDTO.class);
    }

    @GetMapping
    public List<TransactionResponseDTO> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return transactions.stream()
                .map(t -> mapper.map(t, TransactionResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TransactionResponseDTO getTransactionById(@PathVariable("id") Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return mapper.map(transaction, TransactionResponseDTO.class);
    }
}
