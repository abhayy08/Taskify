package com.abhay.taskify.features.feature_note.presentation.notes


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.abhay.taskify.features.feature_note.presentation.components.HexagonShapeBox
import com.abhay.taskify.features.feature_note.presentation.navigation.NotesScreens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesScreen(
    viewModel: NotesViewModel = hiltViewModel(),
    navController: NavHostController,
    paddingValues: PaddingValues = PaddingValues(0.dp),
) {

    val state = viewModel.notesState.value

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy((-98).dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        contentPadding =
        PaddingValues(start = 16.dp, top = paddingValues.calculateTopPadding(), end = 16.dp, bottom = paddingValues.calculateBottomPadding())
    ) {
        itemsIndexed(state.notes) { index, note ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = if (index % 2 != 0) Alignment.End else Alignment.Start
            ) {
                NoteItem(
                    modifier = Modifier
                        .fillMaxWidth(0.55f)
                        .height(200.dp)
                        .clickable {
                            navController.navigate(NotesScreens.AddEditScreen.route + "?noteId=${note.id}")
                        },
                    noteTitle = note.title, noteContent = note.content
                )
            }

        }

    }
}

@Composable
fun NoteItem(
    modifier: Modifier = Modifier, noteTitle: String, noteContent: String
) {
    HexagonShapeBox(
        modifier = modifier,
        borderColor = MaterialTheme.colorScheme.primary.copy(0.3f),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(0.7f),
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
//@Preview
//@Composable
//fun NotesPreview() {
//    TaskifyTheme {
//        NotesScreen(
//            onFabClick = {}
//
//        )
//    }
//}
