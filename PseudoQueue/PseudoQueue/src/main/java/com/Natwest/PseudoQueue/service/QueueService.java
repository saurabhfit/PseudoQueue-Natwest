package com.Natwest.PseudoQueue.service;

import com.Natwest.PseudoQueue.model.Transaction;

public interface QueueService {
    public boolean enqueueTransaction(Transaction transaction);
}
