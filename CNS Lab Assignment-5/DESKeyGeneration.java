import java.util.*;
public class DESKeyGeneration {
    static int[] parityDrop = {
        57,49,41,33,25,17,9,
        1,58,50,42,34,26,18,
        10,2,59,51,43,35,27,
        19,11,3,60,52,44,36,
        63,55,47,39,31,23,15,
        7,62,54,46,38,30,22,
        14,6,61,53,45,37,29,
        21,13,5,28,20,12,4
    };
    static int[] shiftTable = {
        1,1,2,2,2,2,2,2,
        1,2,2,2,2,2,2,1
    };
    static int[] compressionTable = {
        14,17,11,24,1,5,
        3,28,15,6,21,10,
        23,19,12,4,26,8,
        16,7,27,20,13,2,
        41,52,31,37,47,55,
        30,40,51,45,33,48,
        44,49,39,56,34,53,
        46,42,50,36,29,32
    };
    static String hexToBin(String hex) {
        StringBuilder binary = new StringBuilder();
        for(char ch : hex.toCharArray()) {
            binary.append(
                String.format("%4s",
                Integer.toBinaryString(
                Integer.parseInt(String.valueOf(ch),16)))
                .replace(' ','0')
            );
        }
        return binary.toString();
    }
    static String binToHex(String bin) {
        StringBuilder hex = new StringBuilder();
        for(int i=0;i<bin.length();i+=4) {
            String part = bin.substring(i,i+4);
            hex.append(Integer.toHexString(
                    Integer.parseInt(part,2)).toUpperCase());
        }
        return hex.toString();
    }
    static String permute(String input,int[] table) {
        StringBuilder output = new StringBuilder();
        for(int value : table)
            output.append(input.charAt(value-1));
        return output.toString();
    }
    static String shiftLeft(String input,int shifts) {
        return input.substring(shifts)
                + input.substring(0,shifts);
    }
    static List<String> generateRoundKeys(String key64) {
        List<String> roundKeys = new ArrayList<>();
        String key56 = permute(key64,parityDrop);
        String left = key56.substring(0,28);
        String right = key56.substring(28);
        for(int i=0;i<16;i++) {
            left = shiftLeft(left,shiftTable[i]);
            right = shiftLeft(right,shiftTable[i]);
            String combined = left + right;
            String roundKey =
                permute(combined,compressionTable);
            roundKeys.add(roundKey);
        }
        return roundKeys;
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter 16 digit Hex Key : ");
            String keyHex = sc.nextLine().toUpperCase();
            String keyBin = hexToBin(keyHex);
            List<String> keys = generateRoundKeys(keyBin);
            System.out.println("\nRound Keys:");
            for(int i=0;i<16;i++) {
                System.out.println("K"+(i+1)+" : " + binToHex(keys.get(i)));
            }
        }
    }
}
