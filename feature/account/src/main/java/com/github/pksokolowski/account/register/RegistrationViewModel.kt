package com.github.pksokolowski.account.register

import androidx.lifecycle.ViewModel
import com.github.pksokolowski.account.user.domain.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
internal class RegistrationViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState())
    val uiState = _uiState.asStateFlow()

    private fun setState(body: UiState.() -> UiState) {
        _uiState.value = _uiState.value.body()
    }

    fun setPassword1(passwd: String) {
        setState { copy(password1 = passwd) }
    }

    fun setPassword2(passwd: String) {
        setState { copy(password2 = passwd) }
    }

    fun register(){

    }
}

internal enum class Destination {
    Login,
}

internal data class UiState(
    val password1: String = "",
    val password2: String = "",

    val redirectTo: Destination? = null
)