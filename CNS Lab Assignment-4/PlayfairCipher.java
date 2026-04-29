public class PlayfairCipher {

    static char[][] matrix = new char[5][5];

    static void generateMatrix(String key) {
        boolean[] used = new boolean[26];
        key = key.toUpperCase().replace("J", "I");
        int row = 0, col = 0;

        for (char c : key.toCharArray()) {
            if (c >= 'A' && c <= 'Z' && !used[c - 'A']) {
                matrix[row][col++] = c;
                used[c - 'A'] = true;
                if (col == 5) { col = 0; row++; }
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue;
            if (!used[c - 'A']) {
                matrix[row][col++] = c;
                used[c - 'A'] = true;
                if (col == 5) { col = 0; row++; }
            }
        }
    }

    static int[] findPosition(char c) {
        if (c == 'J') c = 'I';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) return new int[]{i, j};
            }
        }
        return null;
    }

    static String prepareText(String text) {
        text = text.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            sb.append(text.charAt(i));
            if (i < text.length() - 1 && text.charAt(i) == text.charAt(i + 1)) {
                sb.append('X');
            } else if (i < text.length() - 1) {
                sb.append(text.charAt(++i));
            }
        }

        if (sb.length() % 2 != 0) sb.append('X');
        return sb.toString();
    }

    static String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int[] a = findPosition(text.charAt(i));
            int[] b = findPosition(text.charAt(i + 1));

            if (a[0] == b[0]) {
                result.append(matrix[a[0]][(a[1] + 1) % 5]).append(matrix[b[0]][(b[1] + 1) % 5]);
            } else if (a[1] == b[1]) {
                result.append(matrix[(a[0] + 1) % 5][a[1]]).append(matrix[(b[0] + 1) % 5][b[1]]);
            } else {
                result.append(matrix[a[0]][b[1]]).append(matrix[b[0]][a[1]]);
            }
        }
        return result.toString();
    }

    static String decrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int[] a = findPosition(text.charAt(i));
            int[] b = findPosition(text.charAt(i + 1));

            if (a[0] == b[0]) {
                result.append(matrix[a[0]][(a[1] + 4) % 5]).append(matrix[b[0]][(b[1] + 4) % 5]);
            } else if (a[1] == b[1]) {
                result.append(matrix[(a[0] + 4) % 5][a[1]]).append(matrix[(b[0] + 4) % 5][b[1]]);
            } else {
                result.append(matrix[a[0]][b[1]]).append(matrix[b[0]][a[1]]);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        generateMatrix("SECURITY");
        String input = "HELLOWORLD";
        String prepared = prepareText(input);
        String encrypted = encrypt(prepared);
        String decrypted = decrypt(encrypted);

        System.out.println("Key: SECURITY");
        System.out.println("Plaintext: " + input);
        System.out.println("Prepared: " + prepared);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
