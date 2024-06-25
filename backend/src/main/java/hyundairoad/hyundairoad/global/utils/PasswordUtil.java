package hyundairoad.hyundairoad.global.utils;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordUtil {
    public static String encrypt(String password) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 키 생성하기
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();

        // 암호화하기
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(password.getBytes());

        // 암호화된 데이터를 Base64로 인코딩하여 출력한다.
        return Base64.getEncoder().encodeToString(encrypted);
    }

//    public static String decode(String encryptedPw) {
//
//    }

//    public static String hashPassword(String password) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hashedBytes = digest.digest(password.getBytes());
//            return Base64.getEncoder().encodeToString(hashedBytes);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException("SHA-256 algorithm not found", e);
//        }
//    }
//
//    public static boolean checkPassword(String rawPassword, String hashedPassword) {
//        return hashPassword(rawPassword).equals(hashedPassword);
//    }
}
