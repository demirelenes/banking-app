package com.demirelenes.bankingapp.transaction.service;

import com.demirelenes.bankingapp.transaction.entity.ATMTransaction;
import com.demirelenes.bankingapp.transaction.entity.Transaction;
import com.demirelenes.bankingapp.transaction.entity.Transfer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface ITransactionService {

    Transaction processAtmTransaction(ATMTransaction atmTransaction, Long accountId);

    Transaction makeTransfer(Transfer transfer, Long sourceId, Long destinationId) throws IOException, ParserConfigurationException, SAXException;

    List<Transaction> getAllTransactions();

    Transaction getTransactionById(Long id);
}
