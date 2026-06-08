import java.math.BigInteger;
public class DiffieHellman {
    public static void main(String[] args) {
        BigInteger q = BigInteger.valueOf(157);
        BigInteger alpha = BigInteger.valueOf(5);
        BigInteger XA = BigInteger.valueOf(15);
        BigInteger XB = BigInteger.valueOf(27);
        BigInteger YA = alpha.modPow(XA, q);
        BigInteger YB = alpha.modPow(XB, q);
        BigInteger KA = YB.modPow(XA, q);
        BigInteger KB = YA.modPow(XB, q);
        System.out.println("YA = " + YA);
        System.out.println("YB = " + YB);
        System.out.println("Shared Key by A = " + KA);
        System.out.println("Shared Key by B = " + KB);
    }
}
