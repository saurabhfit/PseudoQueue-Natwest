package com.Natwest.PseudoQueue.service.impl;

import com.Natwest.PseudoQueue.adaptor.HttpAdaptor;
import com.Natwest.PseudoQueue.model.Transaction;
import com.Natwest.PseudoQueue.payload.ResponsePayload;
import com.Natwest.PseudoQueue.service.PseudoQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class PseudoQueueServiceImpl implements PseudoQueueService {

    @Autowired
    private HttpAdaptor adaptor;

    @Override
    public boolean enqueueTransaction(Transaction transaction) {
        EncryptionService encryptionService = new EncryptionService();
        Transaction encryptedTransaction = new Transaction();
        encryptedTransaction.setAccountNumber(encryptionService.encrypt(transaction.getAccountNumber()));
        encryptedTransaction.setAccountFrom(encryptionService.encrypt(transaction.getAccountFrom()));
        encryptedTransaction.setAmount(encryptionService.encrypt(transaction.getAmount()));
        encryptedTransaction.setCurrency(encryptionService.encrypt(transaction.getCurrency()));
        encryptedTransaction.setType(encryptionService.encrypt(transaction.getType()));

        String url ="http://localhost:2079/api/1.0/queue/addTransaction";
        ResponsePayload responsePayload = adaptor.postMethod(url, new HashMap<>(), ResponsePayload.class, transaction);

        if(responsePayload.getResponseStatus().equals(ResponsePayload.RESPONSE_STATUS.SUCCESS)){
            return true;
        }else{
            return false;
        }
    }

}
