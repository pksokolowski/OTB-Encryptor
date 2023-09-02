package com.github.pksokolowski.account.register

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

internal const val LandingRoute = "account/register"

fun NavController.navigateToRegistration(navOptions: NavOptions? = null) {
    this.navigate(LandingRoute, navOptions)
}

internal fun NavGraphBuilder.registrationScreen() {
    composable(LandingRoute) {
        val viewModel: RegistrationViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        RegistrationScreen(
            uiState = uiState,
            onPassword1Changed = viewModel::setPassword1,
            onPassword2Changed = viewModel::setPassword2,
            onConfirm = viewModel::register,
        )
    }
}