package com.example.guitarguide.screens.lesson

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.guitarguide.AppViewModelProvider
import com.example.guitarguide.ui.theme.Routes
import com.example.myapplication.db.files.File
import com.example.myapplication.db.lessons.Lesson

data class TabItem(
    val title: String
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonScreen(
    viewModel: LessonViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController,
    lessonNumber: String?,
    lessonWatched: String?,
    context: Context) {
    var lessonIsSaved by remember {
        mutableStateOf(viewModel.isLessonSaved(lessonNumber?.toInt() ?: 0))
    }
    var lessonIsWatched by remember {
        mutableStateOf(
            (when (lessonWatched) {
                "true" -> true
                else -> false }
            )
        )
    }
    val watched: (Int) -> Unit = {it -> viewModel.markLessonAsWatched(it)}
    val tabItems = listOf(
        TabItem(
            title = "Теория"
        ),
        TabItem(
            title = "Практика"
        )
    )
    val selectedTabIndex = remember {
        mutableIntStateOf(0)
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Урок $lessonNumber"
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.surface,
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.inversePrimary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            lessonIsSaved = if (lessonIsSaved) {
                                viewModel.unMarkLessonAsSaved(lessonNumber?.toInt() ?: 0)
                                !lessonIsSaved
                            } else {
                                viewModel.markLessonAsSaved(lessonNumber?.toInt() ?: 0)
                                !lessonIsSaved
                            }
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.inversePrimary
                        )
                    ) {
                        Icon(
                            imageVector = (if (lessonIsSaved) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder),
                            contentDescription = "Save"
                        )
                    }
                    val options = listOf("Метроном", "Снять просмотр")
                    var expanded by remember { mutableStateOf(false) }
                    var selectedOptionText by remember { mutableStateOf(options[0]) }

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = it
                        }
                    ) {
                        TextField(
                            modifier = Modifier.menuAnchor().size(40.dp),
                            readOnly = true,
                            value = selectedOptionText,
                            onValueChange = { },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                focusedTrailingIconColor = MaterialTheme.colorScheme.inversePrimary,
                                unfocusedTrailingIconColor = MaterialTheme.colorScheme.inversePrimary,
                                placeholderColor = MaterialTheme.colorScheme.primary,
                                disabledPlaceholderColor = MaterialTheme.colorScheme.primary
                            ),
                            label = { Text("Тип") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expanded
                                )
                            }
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            }
                        ) {
                            options.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption) },
                                    colors = MenuDefaults.itemColors(),
                                    onClick = {
                                        if (selectionOption == options[0]) {
                                            navController.navigate(route = Routes.Metronome.route)
                                        }
                                        else {
                                            if (lessonIsWatched) {
                                                viewModel.unMarkLessonAsWatched(lessonId = lessonNumber?.toInt() ?: 0)
                                            }
                                        }
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = paddingValues.calculateTopPadding()
                )
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex.intValue,
                divider = { Divider(thickness = 3.dp) },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.inversePrimary
            ) {
                tabItems.forEachIndexed { index, tabItem ->
                    Tab(selected = index == selectedTabIndex.intValue,
                        onClick = {
                            selectedTabIndex.intValue = index
                        },
                        text = {
                            Text(text = tabItem.title)
                        })
                }
            }
            val lesson: Lesson = viewModel.getLesson(lessonNumber?.toInt() ?: 0)
            Spacer(modifier = Modifier.size(20.dp))
            val getFiles: (Int) -> (List<File>) = { viewModel.getFilesForLesson(lessonNumber?.toInt() ?: 0) }

            Column(modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(start = 20.dp, top = 5.dp, end = 20.dp)) {
                when (selectedTabIndex.intValue) {
                    0 -> theory(lesson)
                    1 -> practice(lesson, viewModel, context)
                }
            }
        }
    }
}

@Composable
fun theory(lesson: Lesson){
    val les: String = lesson.theory.replace('*', '\n')
    Text(
        text = les,
        lineHeight = 22.sp)
}

@Composable
fun practice(
    lesson: Lesson,
    viewModel: LessonViewModel,
    context: Context){
    val files = viewModel.getFilesForLesson(lesson.id)
    Text(text = lesson.practice)
    Spacer(modifier = Modifier.size(15.dp))
    files.forEach {
        Text(text = it.description)
        Spacer(modifier = Modifier.size(10.dp))
        ClickableText(
            text = AnnotatedString(it.name),
            onClick = { _ ->
                viewModel.downloadFile(context, it)
            },
            style = TextStyle(color = MaterialTheme.colorScheme.inversePrimary, fontSize = 15.sp)
        )
    }
    viewModel.markLessonAsWatched(lesson.id)
}
