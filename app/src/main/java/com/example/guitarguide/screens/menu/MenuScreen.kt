package com.example.guitarguide.screens.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.navigation.NavHostController
import com.example.guitarguide.R
import com.example.guitarguide.ui.theme.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name_rus)
                ) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors (
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.surface,
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(
                space = 12.dp,
                alignment = Alignment.Top
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(50.dp))
            Text(
                text = stringResource(id = (R.string.title_menu_rus)),
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(vertical = paddingValues.calculateTopPadding() )
            )

            Button(
                onClick = {
                    navController.navigate(route = Routes.LessonsList.route)
                },
                modifier = Modifier
                    .width(250.dp)
                    .height(40.dp)
            ) {
                Text(text = stringResource(id = R.string.button_lessons_list))
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(
                onClick = {
                    navController.navigate(route = Routes.Metronome.route)
                },
                modifier = Modifier
                    .width(250.dp)
                    .height(40.dp)
            ) {
                Text(text = stringResource(id = R.string.button_metronomoe))
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(
                onClick = {
                    navController.navigate(route = Routes.Favorites.route)
                },
                modifier = Modifier
                    .width(250.dp)
                    .height(40.dp)
            ) {
                Text(text = stringResource(id = R.string.button_favorites))
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(
                onClick = {
                    navController.navigate(route = Routes.Achievements.route)
                },
                modifier = Modifier
                    .width(250.dp)
                    .height(40.dp)
            ) {
                Text(text = stringResource(id = R.string.button_achievements))
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(
                onClick = {
                    navController.navigate(route = Routes.Settings.route)
                },
                modifier = Modifier
                    .width(250.dp)
                    .height(40.dp)
            ) {
                Text(text = stringResource(id = R.string.button_support))
            }
        }
    }
}
