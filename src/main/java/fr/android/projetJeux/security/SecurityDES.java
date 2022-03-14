package fr.android.projetJeux.security;

import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class SecurityDES {

    public static Key generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56);
        return keyGenerator.generateKey();
    }

    public static byte[] stringToByte(String message){
        return message.getBytes(StandardCharsets.UTF_8);
    }

    public static String byteToString(byte[] message){
        return new String(message, StandardCharsets.UTF_8);
    }

    public static byte[] encode(String message, Key secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        return encode(stringToByte(message),secretKey);
    }

    public static byte[] encode(byte[] message, Key secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(message);
    }

    public static byte[] decode(byte[] message, Key secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(message);
    }
}
