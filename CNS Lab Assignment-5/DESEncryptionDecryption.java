import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
public class DESEncryptionDecryption {
    public static void main(String[] args) throws Exception {
        String keyString = "12345678";
        String plainText = "HelloDES";
        DESKeySpec desKeySpec = new DESKeySpec(keyString.getBytes());
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = factory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        System.out.println("Encrypted:");
        for(byte b : encrypted) {
            System.out.printf("%02X", b);
        }
        System.out.println();
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(encrypted);
        System.out.println("Decrypted: " + new String(decrypted));
    }
}

