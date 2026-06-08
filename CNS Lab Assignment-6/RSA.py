p = 61
q = 53
e = 17
n = p * q
phi = (p - 1) * (q - 1)
d = pow(e, -1, phi)
print("Public Key (e,n):", (e, n))
print("Private Key (d,n):", (d, n))
message = 65
cipher = pow(message, e, n)
print("Encrypted Message:", cipher)
plain = pow(cipher, d, n)
print("Decrypted Message:", plain)
