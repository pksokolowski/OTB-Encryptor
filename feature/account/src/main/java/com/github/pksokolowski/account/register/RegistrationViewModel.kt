package com.github.pksokolowski.account.register

import androidx.lifecycle.ViewModel
import com.github.pksokolowski.account.user.domain.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
internal class RegistrationViewModel @Inject constructor(
    accountRepository: AccountRepository
) : ViewModel() {

    private val _destination = MutableStateFlow<Destination?>(null)
    val destination = _destination.asStateFlow()

    init {
        if (accountRepository.getAccount() != null) {
            _destination.value = Destination.Login
        } else {
            _destination.value = Destination.Registration
        }
    }
}

internal enum class Destination {
    Registration,
    Login,
}