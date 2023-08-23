package com.github.pksokolowski.account.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.pksokolowski.account.landing.domain.DoesAccountExistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class LandingViewModel @Inject constructor(
    doesAccountExistUseCase: DoesAccountExistUseCase
) : ViewModel() {

    private val _destination = MutableStateFlow<Destination?>(null)
    val destination = _destination.asStateFlow()

    init {
        viewModelScope.launch {
            if (doesAccountExistUseCase()) {
                _destination.value = Destination.Login
            } else {
                _destination.value = Destination.Registration
            }
        }
    }
}

internal enum class Destination {
    Registration,
    Login,
}