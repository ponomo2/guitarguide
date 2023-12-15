package com.example.guitarguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.guitarguide.navgraph.SetupNavGraph
import com.example.guitarguide.ui.theme.GuitarGuideTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            GuitarGuideTheme {
                SetupNavGraph(navController = navController, context = this)
            }
        }
    }
}


