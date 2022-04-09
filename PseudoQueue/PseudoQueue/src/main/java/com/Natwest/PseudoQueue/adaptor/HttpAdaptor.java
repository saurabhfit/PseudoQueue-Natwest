package com.Natwest.PseudoQueue.adaptor;

import com.Natwest.PseudoQueue.model.Transaction;
import com.Natwest.PseudoQueue.payload.ResponsePayload;

import java.util.Map;

public interface HttpAdaptor {
    ResponsePayload postMethod(String url, Map<String, String> map, Class responseType, Transaction obj);
}
