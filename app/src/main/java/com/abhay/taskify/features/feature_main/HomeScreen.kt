package com.abhay.taskify.features.feature_main

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.NoteAdd
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AddAlert
import androidx.compose.material.icons.rounded.AddTask
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abhay.taskify.R
import com.abhay.taskify.features.feature_main.navgraphs.HomeNavGraph
import com.abhay.taskify.features.feature_note.presentation.navigation.NotesScreens
import com.abhay.taskify.ui.theme.TaskifyTheme
import kotlinx.coroutines.launch
import java.text.Format

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }

        val screens = listOf(
            FeaturesScreens.Notes, FeaturesScreens.Tasks, FeaturesScreens.Reminders
        )

        DismissibleNavigationDrawer(
            drawerContent = {
                NavigationDrawerContent(
                    selectedItem = selectedItemIndex, onClick = { index, route ->
                        selectedItemIndex = index
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(
                            route = route,
                        ) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }

                    }, screens = screens
                )
            }, drawerState = drawerState
        ) {
            Scaffold(
                topBar = {
                    HomeScreenAppBar(onMenuClick = {
                        scope.launch { drawerState.open() }
                    })
                },
                floatingActionButton = {
                    HomeScreenFloatingActionButton(screens = screens, navController)
                }
            ) {
                HomeNavGraph(navController = navController, paddingValues = it)
            }
        }
    }
}

@Composable
fun HomeScreenFloatingActionButton(
    screens: List<FeaturesScreens>, navController: NavHostController
) {
    val currentRoute by navController.currentBackStackEntryAsState()
    val currentDestination = currentRoute?.destination
    val currentScreen = screens.find { it.route == currentDestination?.route }
    val isFabVisible = screens.any { it.route == currentDestination?.route }

    if (isFabVisible) {
        FloatingActionButton(onClick = {
            when (currentScreen) {
                FeaturesScreens.Notes -> {
                    navController.navigate(NotesScreens.AddEditScreen.route)
                }
                FeaturesScreens.Reminders -> {}
                FeaturesScreens.Tasks -> {}
                null -> {}
            }
        }) {
            when (currentScreen) {
                FeaturesScreens.Notes -> {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.NoteAdd,
                        contentDescription = "Add Note"
                    )
                }
                FeaturesScreens.Reminders -> {
                    Icon(
                        imageVector = Icons.Rounded.AddAlert, contentDescription = "Add a reminder"
                    )
                }
                FeaturesScreens.Tasks -> {
                    Icon(imageVector = Icons.Rounded.AddTask, contentDescription = "Add a task")
                }
                null -> {
                    Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
                }
            }
        }
    }

}

@Composable
fun NavigationDrawerContent(
    modifier: Modifier = Modifier,
    selectedItem: Int,
    onClick: (Int, String) -> Unit = { _, _ -> },
    screens: List<FeaturesScreens>,
) {
    DismissibleDrawerSheet(
        drawerShape = RoundedCornerShape(24.dp),
        drawerTonalElevation = 0.9.dp
    ) {
        Text(text = "Taskify", style = MaterialTheme.typography.headlineMedium,modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 24.dp, bottom = 8.dp))
        Spacer(modifier = Modifier.height(16.dp))
        screens.forEachIndexed { index, screen ->
            NavigationDrawerItem(
                modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                label = {
                    Text(text = screen.title)
                },
                selected = index == selectedItem,
                onClick = { onClick(index, screen.route) },
                icon = {
                    Icon(imageVector = screen.icon, contentDescription = screen.title)
                },
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .align(Alignment.CenterHorizontally),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(0.3f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenAppBar(
    onMenuClick: () -> Unit = {},
) {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Text(text = "Welcome To Taskify")
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = {
                Toast.makeText(context,"You tapped your profile", Toast.LENGTH_SHORT)
                    .show()
            }) {
                Image(
                    painter = painterResource(id = R.drawable.profilephoto),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .border(
                            BorderStroke(
                                1.dp,
                                MaterialTheme.colorScheme.primary
                            ),
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                )
            }
        },
    )

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun HomePreview() {
    TaskifyTheme {
        HomeScreen()
    }
}

