package com.Natwest.PseudoQueue.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
public class Transaction {
    @JsonProperty("transactionId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transactionId;
    @JsonProperty("accountNumber")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accountNumber;
    @JsonProperty("type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;
    @JsonProperty("amount")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String amount;
    @JsonProperty("currency")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String currency;
    @JsonProperty("accountFrom")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accountFrom;

    public Transaction(String accountNumber, String type, String amount, String currency, String accountFrom){
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.accountFrom = accountFrom;
    }
}
