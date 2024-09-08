package com.project.task_manager.utils;

import org.springframework.stereotype.Component;

import java.util.Base64;
@Component
public class CommonUtils {
    public String decodePassword(String encryptedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        // Convert byte array to original string
        return new String(decodedBytes);
    }
}
