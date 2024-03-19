package com.abhay.taskify.features.feature_main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.abhay.taskify.features.feature_main.navgraphs.RootNavGraph
import com.abhay.taskify.ui.theme.TaskifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.Gray.copy(alpha = 0.2f).toArgb(),
                darkScrim = Color.DarkGray.copy(alpha = 0.2f).toArgb()
            )
        )
        setContent {
            TaskifyTheme {
                val rootNavController = rememberNavController()
                RootNavGraph(navHostController = rootNavController)
            }
        }
    }
}
