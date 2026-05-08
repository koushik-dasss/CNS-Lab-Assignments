# ---------------- XOR FUNCTION ---------------- #

def xor(a, b):

    result = ""

    for i in range(len(a)):
        if a[i] == b[i]:
            result += "0"
        else:
            result += "1"

    return result

# ---------------- PERMUTATION FUNCTION ---------------- #

def permute(data, table):

    result = ""

    for i in table:
        result += data[i - 1]

    return result

# ---------------- EXPANSION TABLE ---------------- #

expansion_table = [
    32,1,2,3,4,5,
    4,5,6,7,8,9,
    8,9,10,11,12,13,
    12,13,14,15,16,17,
    16,17,18,19,20,21,
    20,21,22,23,24,25,
    24,25,26,27,28,29,
    28,29,30,31,32,1
]

# ---------------- STRAIGHT P BOX ---------------- #

straight_pbox = [
    16,7,20,21,
    29,12,28,17,
    1,15,23,26,
    5,18,31,10,
    2,8,24,14,
    32,27,3,9,
    19,13,30,6,
    22,11,4,25
]

# ---------------- SIMPLE SBOX ---------------- #
# only demonstration

sbox = [
[
[14,4,13,1],
[2,15,11,8],
[3,10,6,12],
[5,9,0,7]
]
]

# ---------------- SBOX SUBSTITUTION ---------------- #

def sbox_substitution(data):

    # dummy beginner version
    return data[:32]

# ---------------- DES FUNCTION ---------------- #

def des_function(right, round_key):

    expanded = permute(right, expansion_table)

    xored = xor(expanded, round_key)

    sbox_output = sbox_substitution(xored)

    final_output = permute(sbox_output, straight_pbox)

    return final_output

# ---------------- MAIN ---------------- #

print("DES Encryption Skeleton Program")

# Example 32-bit Right Half (R)
right_half = "11110000101010101111000010101010"

# Example 48-bit Round Key (K)
round_key = "000011110000111100001111000011110000111100001111"

# Call the function
result = des_function(right_half, round_key)

print("\nInput R (32-bit):", right_half)
print("Round Key (48-bit):", round_key)
print("Result after f-function:", result)
