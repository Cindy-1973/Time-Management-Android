package com.example.myapplication.ui.navigation

import TimerScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.screen.ListScreen
import com.example.myapplication.ui.screen.MeScreen
import com.example.myapplication.ui.screen.TaskDetailScreen
import com.example.myapplication.ui.screen.TaskScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(
    contentType: ContentType,
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.ListScreen.route
    ) {
        composable(Screens.ListScreen.route) {
            ListScreen(
                viewModel = koinViewModel(),
                onTaskSelected = { taskId ->
                    navHostController.navigate(Screens.TaskDetailScreen.createRoute(taskId))
                }
            )
        }

        composable(Screens.TaskScreen.route) {
            TaskScreen(
                viewModel = koinViewModel(),
                onTaskCreated = { taskId ->
                    navHostController.navigate(Screens.TaskDetailScreen.createRoute(taskId))
                }
            )
        }

        composable(
            route = "${Screens.TaskDetailScreen.route}/{taskId}",
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: return@composable
            TaskDetailScreen(taskId = taskId, onBack = { navHostController.popBackStack() })
        }

        composable(Screens.TimerScreen.route) {
            TimerScreen()
        }
        composable(Screens.MeScreen.route) {
            MeScreen()
        }
    }
}
