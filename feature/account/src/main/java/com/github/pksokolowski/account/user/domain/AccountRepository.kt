package com.github.pksokolowski.account.user.domain

internal interface AccountRepository {
    fun getAccount(): Account?
    fun setAccount(account: Account)
}