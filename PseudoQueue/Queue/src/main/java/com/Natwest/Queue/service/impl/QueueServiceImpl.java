package com.Natwest.Queue.service.impl;

import com.Natwest.Queue.model.Transaction;
import com.Natwest.Queue.repository.TransactionRepository;
import com.Natwest.Queue.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public boolean enqueueTransactionAfterDecryption(Transaction encryptedTransaction) {
        EncryptionService encryptionService = new EncryptionService();
        Transaction decryptedTransaction = new Transaction();
        decryptedTransaction.setAccountNumber(encryptionService.decrypt(encryptedTransaction.getAccountNumber()));
        decryptedTransaction.setAccountFrom(encryptionService.decrypt(encryptedTransaction.getAccountFrom()));
        decryptedTransaction.setAmount(encryptionService.decrypt(encryptedTransaction.getAmount()));
        decryptedTransaction.setCurrency(encryptionService.decrypt(encryptedTransaction.getCurrency()));
        decryptedTransaction.setType(encryptionService.decrypt(encryptedTransaction.getType()));

//        System.out.println(decryptedTransaction.toString());

        transactionRepository.save(decryptedTransaction);

        return true;
    }

}
