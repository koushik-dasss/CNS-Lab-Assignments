# Vigenere Cipher in Python

def generate_key(text, key):
    key = key.upper()
    new_key = ""

    for i in range(len(text)):
        new_key += key[i % len(key)]

    return new_key


# Encryption Function
def encrypt(text, key):
    text = text.upper()
    key = generate_key(text, key)

    cipher = ""

    for i in range(len(text)):
        c = chr((ord(text[i]) + ord(key[i]) - 2 * ord('A')) % 26 + ord('A'))
        cipher += c

    return cipher


# Decryption Function
def decrypt(cipher, key):
    cipher = cipher.upper()
    key = generate_key(cipher, key)

    text = ""

    for i in range(len(cipher)):
        c = chr((ord(cipher[i]) - ord(key[i]) + 26) % 26 + ord('A'))
        text += c

    return text


# Main Program

# Q3 Encryption
plaintext = "CYBERSECURITY"
key1 = "BEST"

encrypted_text = encrypt(plaintext, key1)
print("Encrypted Text:", encrypted_text)

# Q4 Decryption
ciphertext = "CLVMUWPPN"
key2 = "CAKE"

decrypted_text = decrypt(ciphertext, key2)
print("Decrypted Text:", decrypted_text)