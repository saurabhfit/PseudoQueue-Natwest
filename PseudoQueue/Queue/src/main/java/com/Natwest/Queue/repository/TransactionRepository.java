package com.Natwest.Queue.repository;

import com.Natwest.Queue.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository  extends JpaRepository<Transaction, String> {
}
