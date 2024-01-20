package com.github.pksokolowski.cryptography.algorithms

import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

class Aes256Ctr @Inject constructor() {
    fun encrypt(key: ByteArray, plaintext: ByteArray): ByteArray {
        require(key.size == 32)

        val key: SecretKey = SecretKeySpec(key, 0, key.size, "AES")

        val cipher = Cipher.getInstance("AES/CTR/NoPadding").apply {
            init(Cipher.ENCRYPT_MODE, key)
        }

        val ciphertext: ByteArray = cipher.doFinal(plaintext)
        val iv: ByteArray = cipher.iv

        return iv + ciphertext
    }

    fun decrypt(key: ByteArray, nonceAndCipherText: ByteArray): ByteArray {
        require(key.size == 32)

        val nonce = nonceAndCipherText.copyOfRange(0, 12)
        val cipherText = nonceAndCipherText.copyOfRange(13, nonceAndCipherText.lastIndex)

        val key: SecretKey = SecretKeySpec(key, 0, key.size, "AES")

        val cipher = Cipher.getInstance("AES/CTR/NoPadding").apply {
            init(Cipher.DECRYPT_MODE, key, IvParameterSpec(nonce))
        }

        return cipher.doFinal(cipherText)
    }
}