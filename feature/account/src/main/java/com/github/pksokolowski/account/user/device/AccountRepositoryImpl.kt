package com.github.pksokolowski.account.user.device

import com.github.pksokolowski.account.user.domain.Account
import com.github.pksokolowski.account.user.domain.AccountRepository
import javax.inject.Inject

internal class AccountRepositoryImpl @Inject constructor() : AccountRepository {
    override fun getAccount(): Account? {
        return null
    }

    override fun setAccount(account: Account) {
        TODO("Not yet implemented")
    }
}