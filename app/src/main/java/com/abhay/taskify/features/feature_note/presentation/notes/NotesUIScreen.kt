package com.abhay.taskflow.features.feature_note.presentation.notes


import android.annotation.SuppressLint
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.abhay.taskflow.features.feature_main.navgraphs.Graph
import com.abhay.taskflow.features.feature_note.presentation.components.HexagonShapeBox
import com.abhay.taskflow.features.feature_note.presentation.navigation.NotesScreens
import com.abhay.taskflow.ui.theme.TaskFlowTheme

data class Data(
    val title: String, val note: String
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesSection(
    viewModel:  NotesViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    paddingValues: PaddingValues = PaddingValues(0.dp),
    onFabClick: () -> Unit
) {

    val state = viewModel.notesState.value
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .padding(bottom = paddingValues.calculateBottomPadding())
            .scrollable(rememberScrollState(), Orientation.Vertical),
        floatingActionButton = {
            Fab(onClick = onFabClick)
        },
    ) { _ ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy((-98).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = paddingValues.calculateTopPadding() + 16.dp,
                end = 16.dp
            )
        ) {
            itemsIndexed(state.notes) { index,note ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = if (index % 2 != 0) Alignment.End else Alignment.Start
                ) {
                    NoteItem(
                        modifier = Modifier
                            .fillMaxWidth(0.55f)
                            .height(200.dp)
                            .clickable{
                                navController.navigate(NotesScreens.AddEditScreen.route + "?noteId=${note.id}")
                            },
                        noteTitle = note.title,
                        noteContent = note.content
                    )
                }

            }
        }

    }
}

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    noteTitle: String,
    noteContent: String
) {
    HexagonShapeBox(
        modifier = modifier,
        borderColor = MaterialTheme.colorScheme.primary.copy(0.3f),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(0.7f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = noteTitle,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(0.5f)
            )
            Row(
                modifier = Modifier.fillMaxHeight(0.8f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = noteContent,
                    maxLines = 3,
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground.copy(0.75f)
                )
            }
        }
    }
}

@Composable
fun Fab(
    onClick: () -> Unit
) {
    FloatingActionButton(onClick = onClick) {
        Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add Note")
    }
}


@Preview
@Composable
fun NotesPreview() {
    TaskFlowTheme {
        NotesSection(
            onFabClick = {}
        )
    }
}
