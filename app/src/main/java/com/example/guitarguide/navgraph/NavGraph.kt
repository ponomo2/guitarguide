package com.example.guitarguide.navgraph

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.guitarguide.ui.theme.HOME
import com.example.guitarguide.ui.theme.ROOT


@Composable
fun SetupNavGraph(navController: NavHostController, context: Context) {
    NavHost(
        navController = navController,
        startDestination = HOME,
        route = ROOT
    ) {
        HomeNavGraph(navController = navController, context = context)
        LessonNavGraph(navController = navController, context = context)
    }
}
