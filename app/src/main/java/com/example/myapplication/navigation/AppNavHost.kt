package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.screen.*

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestinationRoute: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestinationRoute,
        modifier = modifier
    ) {
        composable(Destination.LIST.route) {
            ListScreen(onEdit = { id ->
                navController.navigate("edit/$id")
            })
        }
        composable(
            route = "edit/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("id") ?: return@composable
            TaskScreen(
                id = taskId,
                onDone = { navController.popBackStack() }
            )
        }
        composable(Destination.TIMER.route) {
            TimerScreen()
        }
        composable(Destination.ME.route) {
            MeScreen()
        }
    }
}
