package com.Natwest.Queue.controller;

import com.Natwest.Queue.model.Transaction;
import com.Natwest.Queue.payload.ResponsePayload;
import com.Natwest.Queue.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/1.0/queue")
public class QueueController {

    @Autowired
    QueueService queueService;

    @RequestMapping(value = "/addTransaction", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponsePayload> enqueueTransaction(@RequestBody Transaction transaction){
        ResponsePayload payload = new ResponsePayload();

        boolean success = queueService.enqueueTransactionAfterDecryption(transaction);
        if(success){
            payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.SUCCESS);
            payload.setResponseMessage("Transaction added to queue successful");
            return ResponseEntity.accepted().body(payload);
        }else{
            payload.setResponseStatus(ResponsePayload.RESPONSE_STATUS.FAILURE);
            payload.setResponseMessage("Unable to add transaction to queue");
            return ResponseEntity.internalServerError().body(payload);
        }
    }

}
