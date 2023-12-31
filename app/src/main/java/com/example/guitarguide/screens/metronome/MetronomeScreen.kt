package com.example.guitarguide.screens.metronome

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.guitarguide.AppViewModelProvider

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MetronomeScreen(
    viewModel: MetronomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = AppViewModelProvider.Factory
    ),
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Метроном"
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
                }
            )
        }
    ) { paddingValues ->
        var state by remember {
            mutableStateOf("60")
        }
        var isError by remember {
            mutableStateOf(false)
        }
        var checked by remember { mutableStateOf(false) }

        fun validateNumber() {
            try {
                when (state.toLong()) {
                    !in viewModel.lowestTempo..viewModel.highestTempo -> {
                        isError = true
                    }
                    else -> isError = false
                }
            } catch (e: NumberFormatException) {
                isError = true
                state = ""
                viewModel.stopMetronome()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        try {
                            state = (state.toLong() - 1).toString()
                            validateNumber()
                        } catch (e: NumberFormatException) {
                            isError = true
                            state = ""
                        }
                    },
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "minus button",
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                    )
                }
                OutlinedTextField(
                    value = state,
                    onValueChange = {
                        state = it
                        validateNumber()
                        if (checked) {
                            viewModel.restartMetronome(state)
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = isError,
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 40.sp),
                    modifier = Modifier
                        .width(120.dp)
                        .height(100.dp),
                )
                IconButton(
                    onClick = {
                        try {
                            state = (state.toLong() + 1).toString()
                            validateNumber()
                        } catch (e: NumberFormatException) {
                            isError = true
                            state = ""
                        }
                    },
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "plus button",
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                    )
                }
            }
            if (isError) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Ритм не может быть более 210 и менее 40",
                    color = MaterialTheme.colorScheme.error
                )
            }
            Spacer(modifier = Modifier.size(100.dp))
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    if (checked) {
                        viewModel.startMetronome(state)
                    } else {
                        viewModel.stopMetronome()
                    }
                },
                modifier = Modifier
                    .scale(1.75f)
            )
        }
    }
}
