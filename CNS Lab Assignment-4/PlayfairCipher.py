matrix = []
def generate_matrix(key):
    global matrix
    key = key.upper().replace("J", "I")
    used = []
    matrix_chars = []
    for ch in key:
        if ch not in used and ch.isalpha():
            used.append(ch)
            matrix_chars.append(ch)
    for ch in "ABCDEFGHIKLMNOPQRSTUVWXYZ":
        if ch not in used:
            used.append(ch)
            matrix_chars.append(ch)
    matrix = [matrix_chars[i:i + 5] for i in range(0, 25, 5)]
def print_matrix():
    print("Playfair Matrix:")
    for row in matrix:
        print(row)
def find_position(ch):
    if ch == 'J':
        ch = 'I'
    for i in range(5):
        for j in range(5):
            if matrix[i][j] == ch:
                return i, j
def prepare_text(text):
    text = text.upper().replace("J", "I")
    prepared = ""
    i = 0
    while i < len(text):
        a = text[i]
        if i + 1 < len(text):
            b = text[i + 1]
            if a == b:
                prepared += a + 'X'
                i += 1
            else:
                prepared += a + b
                i += 2
        else:
            prepared += a + 'X'
            i += 1
    return prepared
def encrypt(text):
    result = ""
    for i in range(0, len(text), 2):
        a = text[i]
        b = text[i + 1]
        row1, col1 = find_position(a)
        row2, col2 = find_position(b)
        if row1 == row2:
            result += matrix[row1][(col1 + 1) % 5]
            result += matrix[row2][(col2 + 1) % 5]
        elif col1 == col2:
            result += matrix[(row1 + 1) % 5][col1]
            result += matrix[(row2 + 1) % 5][col2]
        else:
            result += matrix[row1][col2]
            result += matrix[row2][col1]
    return result
def decrypt(text):
    result = ""
    for i in range(0, len(text), 2):
        a = text[i]
        b = text[i + 1]
        row1, col1 = find_position(a)
        row2, col2 = find_position(b)
        if row1 == row2:
            result += matrix[row1][(col1 - 1) % 5]
            result += matrix[row2][(col2 - 1) % 5]
        elif col1 == col2:
            result += matrix[(row1 - 1) % 5][col1]
            result += matrix[(row2 - 1) % 5][col2]
        else:
            result += matrix[row1][col2]
            result += matrix[row2][col1]
    return result
generate_matrix("MONARCHY")
print_matrix()
plaintext = "INSTRUMENTS"
prepared_text = prepare_text(plaintext)
encrypted_text = encrypt(prepared_text)
print("\nPrepared Text:", prepared_text)
print("Encrypted Text:", encrypted_text)
generate_matrix("SECURITY")
print("\n")
print_matrix()
ciphertext = "FUOQMPXNSPHQ"
decrypted_text = decrypt(ciphertext)
print("\nCipher Text:", ciphertext)
print("Decrypted Text:", decrypted_text)
