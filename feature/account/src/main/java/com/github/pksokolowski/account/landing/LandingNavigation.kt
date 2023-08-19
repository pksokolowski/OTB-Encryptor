package com.github.pksokolowski.account.landing

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

internal const val LandingRoute = "account/landing"

fun NavController.navigateToLanding(navOptions: NavOptions? = null) {
    this.navigate(LandingRoute, navOptions)
}

internal fun NavGraphBuilder.landingScreen() {
    composable(LandingRoute) {
        val viewModel: LandingViewModel = hiltViewModel()
        //val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        LandingScreen(

        )
    }
}