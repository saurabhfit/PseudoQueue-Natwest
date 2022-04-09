package com.Natwest.PseudoQueue.service;

import com.Natwest.PseudoQueue.model.Transaction;

public interface PseudoQueueService {
    public boolean enqueueTransaction(Transaction transaction);
}
