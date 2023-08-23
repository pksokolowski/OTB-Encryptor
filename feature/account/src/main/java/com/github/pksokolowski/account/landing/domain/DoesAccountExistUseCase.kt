package com.github.pksokolowski.account.landing.domain

import com.github.pksokolowski.account.user.domain.AccountRepository
import com.github.pksokolowski.core.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DoesAccountExistUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Boolean = withContext(dispatcher) {
        if (accountRepository.getAccount() == null) return@withContext false
        return@withContext true
    }
}