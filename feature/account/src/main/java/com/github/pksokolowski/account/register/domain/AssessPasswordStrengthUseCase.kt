package com.github.pksokolowski.account.register.domain

import com.github.pksokolowski.core.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class AssessPasswordStrengthUseCase @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(candidate: String): PasswordStrength = withContext(dispatcher) {
        val numOfExtraCharSpacesCovered = numOfExtraCoveredCharSpaces(candidate)
        val uniqueChars = numOfUniqueCharacters(candidate)
        val weightedRepetitionProofedLength = ((2 * candidate.length) + (1 * uniqueChars)) / 3
        val score = (2 * numOfExtraCharSpacesCovered) + (weightedRepetitionProofedLength - 15)

        if (candidate.length < 8 || uniqueChars < 4) {
            return@withContext PasswordStrength.WEAK
        }
        if (candidate.length < 15) {
            return@withContext PasswordStrength.CASUAL
        }
        if (score < 5 || uniqueChars < 7) {
            return@withContext PasswordStrength.CASUAL
        }
        if (score < 8 || numOfExtraCharSpacesCovered < 3) {
            return@withContext PasswordStrength.DECENT
        }
        if (score < 23) {
            return@withContext PasswordStrength.ENTERPRISE
        }
        if (score < 36) {
            return@withContext PasswordStrength.TOP_SECRET
        }

        return@withContext PasswordStrength.MAXED_OUT
    }
}

private fun numOfUniqueCharacters(candidate: String) = candidate.toHashSet().size

/**
 * @return between 0 and 4 (extra spaces covered)
 */
private fun numOfExtraCoveredCharSpaces(candidate: String): Int {
    var spacesCovered = 0

    if (candidate.count { it.isWhitespace() } > 0) spacesCovered++
    if (candidate.count { it.isUpperCase() } > 0) spacesCovered++
    if (candidate.count { it.isLowerCase() } > 0) spacesCovered++
    if (candidate.length - candidate.count { it.isLetterOrDigit() } -
        candidate.count { it.isWhitespace() } > 0) spacesCovered++
    if (candidate.count { it.isDigit() } > 0) spacesCovered++

    return spacesCovered - 1;
}

internal enum class PasswordStrength {
    WEAK,
    CASUAL,
    DECENT, // around 112 bits under some conditions
    ENTERPRISE,  // around 128 bit under some conditions
    TOP_SECRET, // around 192 bit under some conditions
    MAXED_OUT, // around 256 bits under some conditions
}