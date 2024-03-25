package com.abhay.taskify.features.feature_note.presentation.notes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.abhay.taskify.features.feature_note.presentation.navigation.NotesScreens
import com.abhay.taskify.ui.theme.TaskifyTheme


@Composable
fun NotesUiScreen(
    viewModel: NotesViewModel = hiltViewModel(),
    navController: NavHostController,
    paddingValues: PaddingValues = PaddingValues(0.dp),
) {
    val state = viewModel.notesState.value

    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        columns = StaggeredGridCells.Adaptive(156.dp),
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding() + 8.dp,
            start = paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 16.dp,
            end = paddingValues.calculateEndPadding(LayoutDirection.Ltr) + 16.dp,
            bottom = paddingValues.calculateBottomPadding() + 10.dp
        ),
    ) {
        items(state.notes) { note ->
            NoteItem(
                modifier = Modifier
                    .clickable {
                        navController.navigate(NotesScreens.AddEditScreen.route + "?noteId=${note.id}")
                    },
                title = note.title,
                content = note.content
            )
        }
    }
}

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    title: String = "",
    content: String = ""
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(0.5f)),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary.copy(0.07f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            if (title.isNotBlank()) {
                Text(
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = content,
                maxLines = 3,
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground.copy(0.75f),
                textAlign = TextAlign.Justify
            )
        }
    }

}


@Preview
@Composable
fun Previweno() {
    TaskifyTheme {
//        NotesUiScreen2()
    }
}