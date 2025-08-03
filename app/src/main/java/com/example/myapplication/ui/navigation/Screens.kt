package com.example.myapplication.ui.navigation


//enum class Destination(
//    val route: String,
//    val label: String,
//    val icon: ImageVector,
//    val contentDescription: String
//) {
//    LIST("list", "List", Icons.Default.Home, "List"),
//    TASK("task", "Task", Icons.Default.Add, "Task"),
//    TIMER("timer", "Timer", Icons.Default.Lock, "Timer"),
//    ME("me", "Me", Icons.Default.AccountCircle, "Me")
//
//    val DETAIL = "detail/{id}"
//    fun taskDetailRoute(id: Int) = "detail/$id"
//}

sealed class Screens(val route: String) {
    object ListScreen : Screens("list")
    object TaskScreen : Screens("task")
    object TaskDetailScreen : Screens("detail") {
        fun createRoute(taskId: Int) = "$route/$taskId"
    }
    object TimerScreen : Screens("timer")
    object MeScreen : Screens("me")
}