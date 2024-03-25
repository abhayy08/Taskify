package com.abhay.taskify.features.feature_main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.rounded.AddTask
import androidx.compose.material.icons.rounded.NotificationAdd
import androidx.compose.ui.graphics.vector.ImageVector

sealed class FeaturesScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Tasks : FeaturesScreens(
        route = "TASKS",
        title = "Tasks",
        icon = Icons.Rounded.AddTask
    )

    data object Notes : FeaturesScreens(
        route = "NOTES",
        title = "Notes",
        icon = Icons.Outlined.EditNote
    )

    data object Reminders : FeaturesScreens(
        route = "REMINDERS",
        title = "Reminders",
        icon = Icons.Rounded.NotificationAdd
    )
}

