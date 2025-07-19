package com.transacion.transactionservice.service;

import com.transacion.transactionservice.model.Transaction;
import com.transacion.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(String description, Double amount) {
        Transaction transaction = new Transaction(description, amount, LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(String id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction updateTransaction(String id, Transaction transactionDetails) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);

        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transaction.setDescription(transactionDetails.getDescription());
            transaction.setAmount(transactionDetails.getAmount());
            transaction.setDate(LocalDateTime.now()); // Mise à jour de la date
            return transactionRepository.save(transaction);
        } else {
            return null; // Ou vous pouvez lever une exception si la transaction n'existe pas
        }
    }

    public void deleteTransaction(String id) {
        transactionRepository.deleteById(id);
    }
}
