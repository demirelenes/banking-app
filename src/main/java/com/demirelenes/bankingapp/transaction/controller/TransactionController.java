package com.demirelenes.bankingapp.transaction.controller;

import com.demirelenes.bankingapp.transaction.controller.dto.ATMTransactionDTO;
import com.demirelenes.bankingapp.transaction.controller.dto.TransactionResponseDTO;
import com.demirelenes.bankingapp.transaction.controller.dto.TransferDTO;
import com.demirelenes.bankingapp.transaction.entity.ATMTransaction;
import com.demirelenes.bankingapp.transaction.entity.Transaction;
import com.demirelenes.bankingapp.transaction.entity.Transfer;
import com.demirelenes.bankingapp.transaction.service.ITransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

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
    public TransactionResponseDTO deposit(@RequestBody ATMTransactionDTO newTransaction) {
        ATMTransaction transactionEntity = mapper.map(newTransaction, ATMTransaction.class);
        Transaction createdTransaction = transactionService.deposit(transactionEntity);
        return mapper.map(createdTransaction, TransactionResponseDTO.class);
    }

    @PostMapping("/transfer")
    public TransactionResponseDTO makeTransfer(@RequestBody TransferDTO newTransfer) {
        Transfer transferEntity = mapper.map(newTransfer, Transfer.class);
        Transaction createdTransaction = transactionService.makeTransfer(transferEntity);
        return mapper.map(createdTransaction, TransactionResponseDTO.class);
    }

    @GetMapping
    public List<TransactionResponseDTO> getAllTransfers() {
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
