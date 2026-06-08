import java.math.BigInteger;
public class RSA{
    public static void main(String[] args) {
        BigInteger p = BigInteger.valueOf(61);
        BigInteger q = BigInteger.valueOf(53);
        BigInteger e = BigInteger.valueOf(17);
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger d = e.modInverse(phi);
        System.out.println("Public Key: (" + e + "," + n + ")");
        System.out.println("Private Key: (" + d + "," + n + ")");
        BigInteger message = BigInteger.valueOf(65);
        BigInteger cipher = message.modPow(e, n);
        BigInteger decrypted = cipher.modPow(d, n);
        System.out.println("Original Message = " + message);
        System.out.println("Encrypted Message = " + cipher);
        System.out.println("Decrypted Message = " + decrypted);
    }
}

