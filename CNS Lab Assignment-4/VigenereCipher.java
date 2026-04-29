public class VigenereCipher {
    static String generateKey(String text, String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder newKey = new StringBuilder();
        int keyIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                newKey.append(key.charAt(keyIndex % key.length()));
                keyIndex++;
            } else {
                newKey.append(text.charAt(i));
            }
        }
        return newKey.toString();
    }

    static String encrypt(String text, String key) {
        text = text.toUpperCase();
        String fullKey = generateKey(text, key);
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char t = text.charAt(i);
            if (Character.isLetter(t)) {
                char c = (char) ((t + fullKey.charAt(i) - 2 * 'A') % 26 + 'A');
                cipher.append(c);
            } else {
                cipher.append(t);
            }
        }
        return cipher.toString();
    }

    static String decrypt(String cipher, String key) {
        cipher = cipher.toUpperCase();
        String fullKey = generateKey(cipher, key);
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < cipher.length(); i++) {
            char c = cipher.charAt(i);
            if (Character.isLetter(c)) {
                char t = (char) ((c - fullKey.charAt(i) + 26) % 26 + 'A');
                text.append(t);
            } else {
                text.append(c);
            }
        }
        return text.toString();
    }

    public static void main(String[] args) {
        String key = "KEYWORD";
        String originalText = "HELLOWORLD";
        String encrypted = encrypt(originalText, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Original: " + originalText);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}

