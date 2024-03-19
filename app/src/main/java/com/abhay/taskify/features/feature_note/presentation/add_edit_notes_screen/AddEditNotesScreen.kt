@file:OptIn(ExperimentalMaterial3Api::class)

package com.abhay.taskify.features.feature_note.presentation.add_edit_notes_screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.abhay.taskify.features.feature_note.presentation.components.TransparentTextField
import com.abhay.taskify.ui.theme.TaskifyTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditNotesScreen(
    navController: NavHostController,
    viewModel: AddEditNotesViewModel = hiltViewModel(),
) {
    val titleState = viewModel.titleState.value
    val contentState = viewModel.contentState.value
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val isNote = viewModel.isNote

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditNotesViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }

                is AddEditNotesViewModel.UiEvent.ShowSnackBar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(event.message)
                    }
                }

                is AddEditNotesViewModel.UiEvent.DeleteNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    BackHandler {
        viewModel.onEvent(AddEditNoteEvent.SaveNote)
        navController.navigateUp()
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            AddEditTopAppBar(
                navController = navController,
                onDelete = {
                    viewModel.onEvent(AddEditNoteEvent.DeleteNote)
                },
                onSave = {
                         viewModel.onEvent(AddEditNoteEvent.SaveNote)
                },
                isDeleteAvailable = isNote
            )
        },
        floatingActionButton = {
            AddEditFab(
                onClick = {
                    viewModel.onEvent(AddEditNoteEvent.SaveNote)
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 32.dp)
                .padding(paddingValues)
        ) {
            TransparentTextField(
                modifier = Modifier.fillMaxWidth(),
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.onTitleChange(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = TextStyle(
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    fontSize = 25.sp
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentTextField(
                modifier = Modifier.fillMaxSize(),
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.onNoteChange(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                },
                isHintVisible = contentState.isHintVisible,
                singleLine = false,
                textStyle = TextStyle(
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    fontSize = 15.sp
                )
            )

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTopAppBar(
    navController: NavHostController,
    isDeleteAvailable: Boolean,
    onDelete: () -> Unit,
    onSave: () -> Unit
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            if (isDeleteAvailable) {
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "Delete Note"
                    )
                }
            }
            IconButton(onClick = onSave) {
                Icon(
                    imageVector = Icons.Rounded.Save,
                    contentDescription = "Delete Note"
                )
            }
        }
    )
}

@Composable
fun AddEditFab(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(imageVector = Icons.Rounded.Check, contentDescription = "Save Note")
    }
}

@PreviewLightDark
@Composable
fun AddEditNoteScreenPreview() {
    TaskifyTheme {
//        AddEditNotesScreen(navController = rememberNavController())
    }
}