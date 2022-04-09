package com.Natwest.PseudoQueue.controller;

import com.Natwest.PseudoQueue.model.Transaction;
import com.Natwest.PseudoQueue.payload.ResponsePayload;
import com.Natwest.PseudoQueue.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "api/1.0/queue")
public class QueueController {

    @Autowired
    QueueService queueService;

    @RequestMapping(value = "/addTransaction", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponsePayload> enqueueTransaction(@RequestBody Transaction transaction){
        ResponsePayload payload = new ResponsePayload();

        if(validateTransaction(transaction)){
            boolean success = queueService.enqueueTransaction(transaction);
            if(success){
                payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.SUCCESS);
                payload.setResponseMessage("Transaction added to queue successful");
                return ResponseEntity.accepted().body(payload);
            }else{
                payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.FAILURE);
                payload.setResponseMessage("Unable to add transaction to queue");
                return ResponseEntity.internalServerError().body(payload);
            }
        }else{
            payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.FAILURE);
            payload.setResponseMessage("Invalid transaction data");
            return ResponseEntity.badRequest().body(payload);
        }

    }

    private boolean validateTransaction(Transaction transaction){
        if(transaction.getAccountNumber()!=null && !transaction.getAccountNumber().isEmpty() &&
                transaction.getAmount()!=null && !transaction.getAmount().isEmpty() && Double.parseDouble(transaction.getAmount())!=0 &&
                transaction.getCurrency()!=null && !transaction.getCurrency().isEmpty() &&
                transaction.getType()!=null && !transaction.getType().isEmpty() &&
                transaction.getAccountFrom()!=null && !transaction.getAccountFrom().isEmpty())
            return true;
        return false;
    }

}
