package com.example.guitarguide.screens.topic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.guitarguide.AppViewModelProvider
import com.example.guitarguide.R
import com.example.guitarguide.ui.theme.Routes
import com.example.myapplication.db.lessons.Lesson
import com.example.myapplication.db.topics.Topic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonsListScreen(
    viewModel: TopicViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    stringResource(id = (R.string.title_lessons_list))
                ) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors (
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.surface,
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.inversePrimary
                        )) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = paddingValues.calculateTopPadding() + 20.dp,
                    horizontal = 20.dp
                ),
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp,
                alignment = Alignment.Top
            ),
            horizontalAlignment = Alignment.Start
        ) {
            val topics: List<Topic> = viewModel.getAllTopics()
            val watchedLessons: List<Int> = viewModel.getWatchedLessons()
            topics.forEach() {
                Text(
                    text = "Тема ${it.id}. ${it.name}",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary,
                )
                val lessons: List<Lesson>? = viewModel.getLessonsForTopic(it.id)
                lessons?.forEach {
                    Button(
                        onClick = {
                            navController.navigate(route = Routes.Lesson.route
                                    + "/${it.id}"
                                    + "/${
                                        if (it.id in watchedLessons)
                                            "true"
                                        else
                                            "false"}")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                        //, modifier = Modifier.fillMaxWidth().align(Alignment.Start)
                    ) {
                        Text(text = "Урок ${it.id}. ${it.name}",
                            color = (
                                    if (it.id in watchedLessons)
                                        MaterialTheme.colorScheme.inverseSurface
                                    else
                                        MaterialTheme.colorScheme.primary))
                    }
                }
            }
        }
    }
}

