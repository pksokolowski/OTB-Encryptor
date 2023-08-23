package com.github.pksokolowski.account.landing.domain

import com.github.pksokolowski.account.user.domain.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DoesAccountExistUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): Boolean = withContext(dispatcher) {
        if (accountRepository.getAccount() == null) return@withContext false
        return@withContext true
    }
}