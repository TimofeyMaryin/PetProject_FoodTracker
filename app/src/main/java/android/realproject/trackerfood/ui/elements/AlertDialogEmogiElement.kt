package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlertDialogEmogiElement(
    addFoodViewModel: AddFoodViewModel
) {

    LazyVerticalGrid(
        GridCells.Fixed(6),
    ) {
        items(addFoodViewModel.listOfEmoji.size) {
            Text(
                text = addFoodViewModel.listOfEmoji[it],
                modifier = Modifier
                    .clickable {
                        addFoodViewModel.emojiToFood = addFoodViewModel.listOfEmoji[it]
                        addFoodViewModel.closeAlertDialog()
                    }
                    .padding(5.dp),
                style = MaterialTheme.typography.h5
            )
        }
    }
}