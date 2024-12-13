import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class main {


    static class Encryption {
        private static final int TAG_LEN = 16; // 128-bit
        private static final int IV_LEN = 12; // IV length

        public static byte[] encrypt(String plaintext,SecretKey key,byte[] iv) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec parameterSpec = new GCMParameterSpec(TAG_LEN*8, iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
            return cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        }

        public static String decrypt(byte[] ciphertext,SecretKey key,byte[] iv) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec parameterSpec = new GCMParameterSpec(TAG_LEN*8, iv);
            cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
            return new String(cipher.doFinal(ciphertext), StandardCharsets.UTF_8);
        }
    }

    static class AEncryption {
        public static byte[] encrypt(String plaintext, PublicKey key) throws Exception {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        }

        public static String decrypt(byte[] ciphertext, PrivateKey key) throws Exception {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(ciphertext), StandardCharsets.UTF_8);
        }
    }

    // Digital Signing
    static class DigitalSign {
        public static byte[] sign(String message, PrivateKey key) throws Exception {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(key);
            signature.update(message.getBytes(StandardCharsets.UTF_8));
            return signature.sign();
        }

        public static boolean verify(String message, byte[] signatureBytes, PublicKey key) throws Exception {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(key);
            signature.update(message.getBytes(StandardCharsets.UTF_8));
            return signature.verify(signatureBytes);
        }
    }

    public static void main(String[] args) throws Exception {
        // Symmetric Encryption/Decryption
        System.out.println("====== Symmetric Encryption ======");
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // AES-256
        SecretKey sharedKey = keyGen.generateKey();
        byte[] iv = SecureRandom.getSeed(12);

        String message = "Hello, Bob!";
        byte[] encryptedMessage = Encryption.encrypt(message,sharedKey,iv);
        System.out.println("Encrypted: "+Base64.getEncoder().encodeToString(encryptedMessage));
        String decryptedMessage = Encryption.decrypt(encryptedMessage,sharedKey,iv);
        System.out.println("Decrypted: "+decryptedMessage);

        // Asymmetric Encryption/Decryption
        System.out.println("\n====== Asymmetric Encryption ======");
        KeyPairGenerator rsaKeyGen = KeyPairGenerator.getInstance("RSA");
        rsaKeyGen.initialize(2048);
        KeyPair aliceKeyPair = rsaKeyGen.generateKeyPair();
        KeyPair bobKeyPair = rsaKeyGen.generateKeyPair();

        String secretMessage = "Hello, Alice!";
        byte[] rsaEncrypted = AEncryption.encrypt(secretMessage, bobKeyPair.getPublic());
        System.out.println("Encrypted: "+Base64.getEncoder().encodeToString(rsaEncrypted));
        String rsaDecrypted = AEncryption.decrypt(rsaEncrypted, bobKeyPair.getPrivate());
        System.out.println("Decrypted: "+rsaDecrypted);

        // Digital Signing
        System.out.println("\n====== Digital Signing ======");
        String signingMessage = "This is a signed message from Alice.";
        byte[] signature = DigitalSign.sign(signingMessage, aliceKeyPair.getPrivate());
        System.out.println("Signature: "+Base64.getEncoder().encodeToString(signature));
        boolean isVerified = DigitalSign.verify(signingMessage,signature,aliceKeyPair.getPublic());
        System.out.println("Signature Verified: "+isVerified);
    }
}
