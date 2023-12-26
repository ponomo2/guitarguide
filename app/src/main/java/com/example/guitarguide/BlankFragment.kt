package com.example.guitarguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.compose.rememberNavController
import com.example.guitarguide.navgraph.SetupNavGraph
import com.example.guitarguide.ui.theme.GuitarGuideTheme

class BlankFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply { 
            setContent {
                val navController = rememberNavController()
                GuitarGuideTheme {
                    SetupNavGraph(navController = navController, context = requireContext())
                }
            }
        }
    }

}