package com.github.pksokolowski.cryptography.algorithms

import javax.inject.Inject

/**
 * Encrypts/Decrypts provided [ByteArray] using Triple AES with 2 keys in EDE formula with CTR mode.
 * Only 256bit keys are allowed.
 */
class TripleAes @Inject constructor(
    private val aes: Aes256Ctr,
) {
    @Suppress("UnnecessaryVariable")
    fun encrypt(key1: ByteArray, key2: ByteArray, plaintext: ByteArray): ByteArray {
        require(key1.size == 32)
        require(key2.size == 32)

        val ciphertextA = aes.encrypt(key1, plaintext)
        val ciphertextB = aes.decrypt(key2, ciphertextA)
        val ciphertextC = aes.encrypt(key1, ciphertextB)

        return ciphertextC
    }

    @Suppress("UnnecessaryVariable")
    fun decrypt(key1: ByteArray, key2: ByteArray, ciphertext: ByteArray): ByteArray {
        require(key1.size == 32)
        require(key2.size == 32)

        val ciphertextB = aes.decrypt(key1, ciphertext)
        val ciphertextA = aes.encrypt(key2, ciphertextB)
        val plaintext = aes.decrypt(key1, ciphertextA)

        return plaintext
    }
}