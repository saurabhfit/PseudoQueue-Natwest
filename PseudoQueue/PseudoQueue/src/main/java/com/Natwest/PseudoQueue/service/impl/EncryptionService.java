package com.Natwest.PseudoQueue.service.impl;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class EncryptionService {
    private String ALGORITHM;
    private byte[] KEY;

    @Autowired
    public Environment env;

    public EncryptionService(){
        ALGORITHM = env.getProperty("data.encryption.algorithm");
        KEY = env.getProperty("data.encryption.key").getBytes();
    }

    public String encrypt(String data){
        Key key = new SecretKeySpec(KEY, "AES");
        try{
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            return Base64.encodeBase64String(c.doFinal(data.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public String decrypt(String encryptedData) {
        Key key = new SecretKeySpec(KEY, "AES");
        try {
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            return new String(c.doFinal(Base64.decodeBase64(encryptedData)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedData;
    }
}
