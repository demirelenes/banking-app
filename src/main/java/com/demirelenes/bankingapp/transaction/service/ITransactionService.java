package com.demirelenes.bankingapp.transaction.service;

import com.demirelenes.bankingapp.transaction.entity.ATMTransaction;
import com.demirelenes.bankingapp.transaction.entity.Transaction;
import com.demirelenes.bankingapp.transaction.entity.Transfer;

import java.util.List;

public interface ITransactionService {

    Transaction deposit(ATMTransaction atmTransaction);

    Transaction makeTransfer(Transfer transfer);

    List<Transaction> getAllTransactions();

    Transaction getTransactionById(Long id);
}
