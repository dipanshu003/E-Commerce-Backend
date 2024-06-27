package com.amazon.apis.utility;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {

    public static String secretKeyGenerator() {
        // Generate a random byte array
        byte[] keyBytes = new byte[32]; // 256 bits
        new SecureRandom().nextBytes(keyBytes);

        String secretKey = Base64.getEncoder().encodeToString(keyBytes);
    
        return secretKey;
    }
}
