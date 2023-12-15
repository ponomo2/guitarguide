package com.example.guitarguide.navgraph

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.guitarguide.screens.achievements.AchieveScreen
import com.example.guitarguide.screens.menu.MenuScreen
import com.example.guitarguide.screens.metronome.MetronomeScreen
import com.example.guitarguide.screens.saved.FavoritesScreen
import com.example.guitarguide.screens.support.SettingsScreen
import com.example.guitarguide.screens.topic.LessonsListScreen
import com.example.guitarguide.ui.theme.HOME
import com.example.guitarguide.ui.theme.Routes

fun NavGraphBuilder.HomeNavGraph(navController: NavHostController, context: Context) {
    navigation(
        startDestination = Routes.Menu.route,
        route = HOME
    ) {
        composable(
            route = Routes.Menu.route
        ) {
            MenuScreen(navController)
        }

        composable(
            route = Routes.LessonsList.route
        ) {
            LessonsListScreen(navController = navController)
        }

        composable(
            route = Routes.Settings.route
        ) {
            SettingsScreen(navController, context)
        }

        composable(
            route = Routes.Achievements.route
        ) {
            AchieveScreen(navController = navController)
        }

        composable(
            route = Routes.Metronome.route
        ) {
            MetronomeScreen(navController = navController)
        }

        composable(
            route = Routes.Favorites.route
        ) {
            FavoritesScreen(navController = navController)
        }
    }
}