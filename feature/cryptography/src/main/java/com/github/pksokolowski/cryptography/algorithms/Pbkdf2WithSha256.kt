package com.github.pksokolowski.cryptography.algorithms

import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.inject.Inject

class Pbkdf2WithSha256 @Inject constructor() {
    /**
     * Derives a 256 bit key from a provided password.
     */
    fun deriveKey(password: String, iterationsCount: Int = 500_000): ByteArray {
        val salt = ByteArray(128)
        val random = SecureRandom()
        random.nextBytes(salt)

        val keyLen = 256
        val keySpec = PBEKeySpec(password.toCharArray(), salt, iterationsCount, keyLen)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")

        val key = factory.generateSecret(keySpec).encoded
        require(key.size == 32)

        return key
    }
}