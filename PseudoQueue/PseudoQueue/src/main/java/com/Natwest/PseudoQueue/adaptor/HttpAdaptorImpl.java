package com.Natwest.PseudoQueue.adaptor;

import com.Natwest.PseudoQueue.model.Transaction;
import com.Natwest.PseudoQueue.payload.ResponsePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;
import java.util.Map;

@Service
public class HttpAdaptorImpl implements HttpAdaptor{

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public ResponsePayload postMethod(String url, Map<String, String> map, Class responseType, Transaction obj) {
        try{
            HttpEntity<Transaction> httpEntity = new HttpEntity<Transaction>(obj);
            ResponseEntity<ResponsePayload> responseEntity = restTemplate().exchange(url, HttpMethod.POST, httpEntity, ResponsePayload.class, map);
            return responseEntity.getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
