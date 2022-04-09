package com.Natwest.Queue.service;

import com.Natwest.Queue.model.Transaction;

public interface QueueService {
    public boolean enqueueTransactionAfterDecryption(Transaction transaction);
}
