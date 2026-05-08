# ---------------- PARITY DROP TABLE ---------------- #

parity_drop_table = [
    57,49,41,33,25,17,9,
    1,58,50,42,34,26,18,
    10,2,59,51,43,35,27,
    19,11,3,60,52,44,36,
    63,55,47,39,31,23,15,
    7,62,54,46,38,30,22,
    14,6,61,53,45,37,29,
    21,13,5,28,20,12,4
]

# ---------------- SHIFT TABLE ---------------- #

shift_table = [
    1, 1, 2, 2,
    2, 2, 2, 2,
    1, 2, 2, 2,
    2, 2, 2, 1
]

# ---------------- COMPRESSION TABLE ---------------- #

compression_table = [
    14,17,11,24,1,5,
    3,28,15,6,21,10,
    23,19,12,4,26,8,
    16,7,27,20,13,2,
    41,52,31,37,47,55,
    30,40,51,45,33,48,
    44,49,39,56,34,53,
    46,42,50,36,29,32
]

# ---------------- HEX TO BINARY ---------------- #

def hex_to_bin(s):
    mp = {
        '0':"0000",'1':"0001",'2':"0010",'3':"0011",
        '4':"0100",'5':"0101",'6':"0110",'7':"0111",
        '8':"1000",'9':"1001",'A':"1010",'B':"1011",
        'C':"1100",'D':"1101",'E':"1110",'F':"1111"
    }

    binary = ""

    for ch in s:
        binary += mp[ch]

    return binary

# ---------------- BINARY TO HEX ---------------- #

def bin_to_hex(s):

    mp = {
        "0000":'0',"0001":'1',"0010":'2',"0011":'3',
        "0100":'4',"0101":'5',"0110":'6',"0111":'7',
        "1000":'8',"1001":'9',"1010":'A',"1011":'B',
        "1100":'C',"1101":'D',"1110":'E',"1111":'F'
    }

    hex_value = ""

    for i in range(0, len(s), 4):
        chunk = s[i:i+4]
        hex_value += mp[chunk]

    return hex_value

# ---------------- PERMUTATION ---------------- #

def permute(data, table):

    result = ""

    for i in table:
        result += data[i-1]

    return result

# ---------------- LEFT SHIFT ---------------- #

def shift_left(data, n):

    return data[n:] + data[:n]

# ---------------- GENERATE ROUND KEYS ---------------- #

def generate_round_keys(key_64bit):

    round_keys = []

    # parity drop
    key_56 = permute(key_64bit, parity_drop_table)

    left = key_56[:28]
    right = key_56[28:]

    for i in range(16):

        left = shift_left(left, shift_table[i])
        right = shift_left(right, shift_table[i])

        combined = left + right

        round_key = permute(combined, compression_table)

        round_keys.append(round_key)

    return round_keys

# ---------------- MAIN ---------------- #

key_hex = input("Enter 16-digit hexadecimal key: ").upper()

key_binary = hex_to_bin(key_hex)

round_keys = generate_round_keys(key_binary)

print("\n16 Round Keys:\n")

for i in range(16):
    print("K", i+1, ":", bin_to_hex(round_keys[i]))