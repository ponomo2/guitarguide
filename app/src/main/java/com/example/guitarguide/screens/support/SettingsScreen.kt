package com.example.guitarguide.screens.support

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController, context: Context) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Обратная связь"
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
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var openAlertDialog by remember { mutableStateOf(true) }
            when {
                openAlertDialog -> {
                    CustomAlertDialog(
                        onDismissRequest = { openAlertDialog = false },
                        onConfirmation = {
                            openAlertDialog = false

                            // логика создания наполнения письма и его отправки
                            val email = "supp0rt_app@mail.ru"
                            val subject = "Сообщение об ошибке"
                            val message = "Произошла ошибка"

                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                type = "text/plain"
                                data = Uri.parse("mailto:$email?subject=$subject&body=$message")
                            }

                            try{
                                context.startActivity(Intent.createChooser(intent, "Send Email"))
                            } catch (e: Exception) {
                                Toast.makeText(context, "Произошла ошибка", Toast.LENGTH_SHORT).show()
                            }
                        },
                        dialogTitle = "Переход в приложение",
                        dialogText = "Для отправки сообщения об ошибке будет использован Ваш почтовый аккаунт. Желаете продолжить?"
                    )
                }
            }
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    openAlertDialog = true
                }
            ) {
                Text(text = "Отправить сообщение об ошибке")
            }
        }
    }
}

@Composable
fun CustomAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle) },
        text = {
            Text(text = dialogText) },
        onDismissRequest = {
            onDismissRequest() },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation() }
            ) {
                Text("Продолжить")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest() }
            ) {
                Text("Отмена")
            }
        }
    )
}
