package com.example.guitarguide.navgraph

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.guitarguide.screens.lesson.LessonScreen
import com.example.guitarguide.screens.topic.LessonsListScreen
import com.example.guitarguide.ui.theme.LESSONS
import com.example.guitarguide.ui.theme.Routes

fun NavGraphBuilder.LessonNavGraph(navController: NavHostController, context: Context) {
    navigation(
        startDestination = Routes.LessonsList.route,
        route = LESSONS
    ) {
        composable(
            route = Routes.LessonsList.route
        ) {
            LessonsListScreen(navController = navController)
        }
        composable(
            route = Routes.Lesson.route + "/{lesson_number}" + "/{lesson_watched}",
            arguments = listOf(
                navArgument(
                    name = "lesson_number"

                ) {
                    type = NavType.StringType
                    defaultValue = "1"
                    nullable = true
                },
                navArgument(
                    name = "lesson_watched"

                ) {
                    type = NavType.StringType
                    defaultValue = "false"
                    nullable = true
                }
            )
        ) {entry ->
            LessonScreen(
                navController = navController,
                lessonNumber = entry.arguments?.getString("lesson_number"),
                lessonWatched = entry.arguments?.getString("lesson_watched"),
                context = context)
        }

    }
}