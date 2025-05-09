package com.he181435.security;

import javax.crypto.*;
import java.security.*;
import java.util.Base64;

public class Algorithm {
    public String hashMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md5.digest(input.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    public String hashSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = sha.digest(input.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    public String RSA(String input, String method) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] bytes1 = cipher.doFinal(input.getBytes());
        String encoded = Base64.getEncoder().encodeToString(bytes1);

        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        byte[] bytes2 = cipher.doFinal(input.getBytes());
        String decoded = Base64.getEncoder().encodeToString(bytes2);
        return method.equals("encrypt") ? encoded : decoded;

    }

    public <T> Object SignAndVerify(String input, String method) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("SHA256withRSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(keyPair.getPrivate());
        signature.update(input.getBytes());
        byte[] signatureBytes = signature.sign();
        String signatureStr = Base64.getEncoder().encodeToString(signatureBytes);

        Signature verifySig = Signature.getInstance("SHA256withRSA");
        verifySig.initVerify(keyPair.getPublic());
        verifySig.update(input.getBytes());

        boolean isValid = verifySig.verify(signatureBytes);
        
        return method.equals("Sign") ? signatureStr : isValid;
    }

    public void AES(String[] args) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal("Hello AES".getBytes());
        String encoded = Base64.getEncoder().encodeToString(encrypted);

        System.out.println("Encrypted: " + encoded);

        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encoded));
        System.out.println("Decrypted: " + new String(decrypted));
    }
}
