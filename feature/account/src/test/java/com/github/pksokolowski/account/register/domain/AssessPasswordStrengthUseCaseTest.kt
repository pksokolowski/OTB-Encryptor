package com.github.pksokolowski.account.register.domain

import com.github.pksokolowski.account.register.domain.PasswordStrength.CASUAL
import com.github.pksokolowski.account.register.domain.PasswordStrength.ENTERPRISE
import com.github.pksokolowski.account.register.domain.PasswordStrength.MAXED_OUT
import com.github.pksokolowski.account.register.domain.PasswordStrength.DECENT
import com.github.pksokolowski.account.register.domain.PasswordStrength.TOP_SECRET
import com.github.pksokolowski.account.register.domain.PasswordStrength.WEAK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AssessPasswordStrengthUseCaseTest {

    private fun TestScope.prepareSut() = AssessPasswordStrengthUseCase(
        dispatcher = StandardTestDispatcher(testScheduler)
    )

    @Test
    fun `A very short password`() = runTest {
        whenPasswordCandidateIs("kopkop")
            .thenVerdictIs(WEAK)
    }

    @Test
    fun `A merely 8 char password with just one character repeating`() =
        whenPasswordCandidateIs("aaaaaaaa")
            .thenVerdictIs(WEAK)

    @Test
    fun `A merely 8 char password with 4 unique characters repeating`() =
        whenPasswordCandidateIs("abcdabcd")
            .thenVerdictIs(CASUAL)

    @Test
    fun `A mix of 4 different characters (and numbers) across 16 characters`() =
        whenPasswordCandidateIs("aaaabbbb11112222")
            .thenVerdictIs(CASUAL)

    @Test
    fun `A mix of 7 different characters (and numbers) across 16 characters`() =
        whenPasswordCandidateIs("abcdef1aabcdef1a")
            .thenVerdictIs(CASUAL)

    @Test
    fun `A mix of 11 different characters (and numbers) across 23 characters`() =
        whenPasswordCandidateIs("abcdef1aabcdef1a2222222")
            .thenVerdictIs(DECENT)

    @Test
    fun `A reasonable passphrase with medium length`() =
        whenPasswordCandidateIs("karczma stoi otworem przed przejezdnymi")
            .thenVerdictIs(DECENT)

    @Test
    fun `A mix of 13 different characters across 4 charsets across 25 characters`() =
        whenPasswordCandidateIs("abcdef1aabcdef1a2222222_H")
            .thenVerdictIs(ENTERPRISE)

    @Test
    fun `A strengthened passphrase with medium length`() =
        whenPasswordCandidateIs("Karczma stoi otworem przed 3 przejezdnymi.")
            .thenVerdictIs(TOP_SECRET)

    @Test
    fun `A much more strengthened passphrase with medium length`() =
        whenPasswordCandidateIs("Karczma stoi otworem przed 3 przejezdnymi, których wóz przypomina samolot")
            .thenVerdictIs(MAXED_OUT)

    // --- test setup ---

    private data class TestState(
        val candidate: String
    )

    private fun whenPasswordCandidateIs(candidate: String) = TestState(candidate)

    private fun TestState.thenVerdictIs(passStrength: PasswordStrength) {
        runTest {
            val actual = prepareSut()(candidate)
            assertEquals("for password candidate: \"${candidate}\"", passStrength, actual)
        }
    }
}