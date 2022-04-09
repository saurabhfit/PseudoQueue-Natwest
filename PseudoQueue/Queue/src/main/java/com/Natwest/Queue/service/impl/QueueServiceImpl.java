package com.Natwest.Queue.service.impl;

import com.Natwest.Queue.model.Transaction;
import com.Natwest.Queue.service.QueueService;
import org.springframework.stereotype.Service;

@Service
public class QueueServiceImpl implements QueueService {

    @Override
    public boolean enqueueTransactionAfterDecryption(Transaction transaction) {
        EncryptionService encryptionService = new EncryptionService();
        Transaction encryptedTransaction = new Transaction();
        encryptedTransaction.setAccountNumber(encryptionService.decrypt(transaction.getAccountNumber()));
        encryptedTransaction.setAccountFrom(encryptionService.decrypt(transaction.getAccountFrom()));
        encryptedTransaction.setAmount(encryptionService.decrypt(transaction.getAmount()));
        encryptedTransaction.setCurrency(encryptionService.decrypt(transaction.getCurrency()));
        encryptedTransaction.setType(encryptionService.decrypt(transaction.getType()));

        System.out.println(encryptedTransaction.toString());



        return true;
    }

}
