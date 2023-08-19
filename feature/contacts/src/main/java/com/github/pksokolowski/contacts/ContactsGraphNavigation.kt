package com.github.pksokolowski.contacts

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation

private const val Route = "contacts"

fun NavController.navigateToContactsGraph(navOptions: NavOptions? = null) {
    this.navigate(Route, navOptions)
}

fun NavGraphBuilder.contactsGraph(navController: NavController) {
    navigation(
        startDestination = "mock",
        route = Route
    ) {

    }
}