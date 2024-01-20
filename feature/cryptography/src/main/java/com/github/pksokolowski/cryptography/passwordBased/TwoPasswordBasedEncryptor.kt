package com.github.pksokolowski.cryptography.passwordBased

import com.github.pksokolowski.cryptography.algorithms.Pbkdf2WithSha256
import com.github.pksokolowski.cryptography.algorithms.TripleAes
import javax.inject.Inject

class TwoPasswordBasedEncryptor @Inject constructor(
    private val pbkdf2WithSha256: Pbkdf2WithSha256,
    private val tripleAes: TripleAes,
) {
    fun encrypt(pass1: String, pass2: String, plaintext: ByteArray): ByteArray {
        val key1 = pbkdf2WithSha256.deriveKey(pass1)
        val key2 = pbkdf2WithSha256.deriveKey(pass2)

        return tripleAes.encrypt(key1, key2, plaintext)
    }

    fun decrypt(pass1: String, pass2: String, ciphertext: ByteArray): ByteArray {
        val key1 = pbkdf2WithSha256.deriveKey(pass1)
        val key2 = pbkdf2WithSha256.deriveKey(pass2)

        return tripleAes.decrypt(key1, key2, ciphertext)
    }
}