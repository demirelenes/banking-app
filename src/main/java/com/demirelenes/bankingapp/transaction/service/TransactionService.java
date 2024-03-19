package com.demirelenes.bankingapp.transaction.service;

import com.demirelenes.bankingapp.transaction.entity.ATMTransaction;
import com.demirelenes.bankingapp.transaction.entity.Transaction;
import com.demirelenes.bankingapp.transaction.entity.Transfer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {
    @Override
    public Transaction deposit(ATMTransaction atmTransaction) {
        return null;
    }

    @Override
    public Transaction makeTransfer(Transfer transfer) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return null;
    }
}
