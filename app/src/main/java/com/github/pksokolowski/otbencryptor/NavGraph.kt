package com.github.pksokolowski.otbencryptor

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.github.pksokolowski.account.AccountGraphRoutePattern
import com.github.pksokolowski.account.accountGraph
import com.github.pksokolowski.contacts.contactsGraph

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AccountGraphRoutePattern,
        modifier = modifier
    ) {
        accountGraph(navController)
        contactsGraph(navController)
    }
}