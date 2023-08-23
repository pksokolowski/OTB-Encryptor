package com.github.pksokolowski.account

import com.github.pksokolowski.account.user.device.AccountRepositoryImpl
import com.github.pksokolowski.account.user.domain.AccountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class AccountModule {
    @Binds
    abstract fun bindAccountRepository(impl: AccountRepositoryImpl): AccountRepository
}