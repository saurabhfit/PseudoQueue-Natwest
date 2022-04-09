package com.Natwest.PseudoQueue.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String transactionId;
    private String accountNumber;
    private String type;
    private String amount;
    private String currency;
    private String accountFrom;

    public Transaction(String accountNumber, String type, String amount, String currency, String accountFrom){
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.accountFrom = accountFrom;
    }
}
