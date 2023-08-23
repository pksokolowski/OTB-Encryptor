package com.github.pksokolowski.account.register

import androidx.hilt.navigation.compose.hiltViewModel
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
        //val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        LandingScreen(

        )
    }
}