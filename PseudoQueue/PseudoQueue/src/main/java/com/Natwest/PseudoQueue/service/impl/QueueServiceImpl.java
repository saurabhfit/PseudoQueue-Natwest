package com.Natwest.PseudoQueue.service.impl;

import com.Natwest.PseudoQueue.model.Transaction;
import com.Natwest.PseudoQueue.service.QueueService;
import org.springframework.stereotype.Service;

@Service
public class QueueServiceImpl implements QueueService {

    @Override
    public boolean enqueueTransaction(Transaction transaction) {
        EncryptionService encryptionService = new EncryptionService();
        Transaction encryptedTransaction = new Transaction();
        encryptedTransaction.setAccountNumber(encryptionService.encrypt(transaction.getAccountNumber()));
        encryptedTransaction.setAccountFrom(encryptionService.encrypt(transaction.getAccountFrom()));
        encryptedTransaction.setAmount(encryptionService.encrypt(transaction.getAmount()));
        encryptedTransaction.setCurrency(encryptionService.encrypt(transaction.getCurrency()));
        encryptedTransaction.setType(encryptionService.encrypt(transaction.getType()));


        return true;
    }

}
