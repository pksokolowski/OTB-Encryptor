package com.github.pksokolowski.account.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.pksokolowski.account.register.domain.AssessPasswordStrengthUseCase
import com.github.pksokolowski.account.register.domain.PasswordStrength
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class RegistrationViewModel @Inject constructor(
    private val assessPasswordStrengthUseCase: AssessPasswordStrengthUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private fun setState(body: UiState.() -> UiState) {
        _uiState.value = _uiState.value.body()
    }

    fun setPassword1(newValue: String) {
        setState { copy(password1 = password1.copy(password = newValue)) }
        updatePasswordsStrengthEstimations()
    }

    fun setPassword2(newValue: String) {
        setState { copy(password2 = password2.copy(password = newValue)) }
        updatePasswordsStrengthEstimations()
    }

    fun register() {
        updatePasswordsStrengthEstimations()
    }

    private fun updatePasswordsStrengthEstimations() {
        viewModelScope.launch {
            val passwords = listOf(_uiState.value.password1, _uiState.value.password2)

            val strength = passwords.map {
                async {
                    assessPasswordStrengthUseCase(it.password)
                }
            }.awaitAll()

            setState {
                copy(
                    password1 = PasswordInputState(password1.password, strength[0]),
                    password2 = PasswordInputState(password2.password, strength[1]),
                )
            }
        }
    }
}

internal enum class Destination {
    Login,
}

internal data class PasswordInputState(
    val password: String,
    val strengthAssessment: PasswordStrength?,
)

internal data class UiState(
    val password1: PasswordInputState = PasswordInputState("", null),
    val password2: PasswordInputState = PasswordInputState("", null),
    val error: Error? = null,

    val redirectTo: Destination? = null
)