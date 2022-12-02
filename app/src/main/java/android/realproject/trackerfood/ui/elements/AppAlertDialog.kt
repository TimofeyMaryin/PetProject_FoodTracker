package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.viewModelFactory.AddFoodViewModelFactory
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppAlertDialog(
    addFoodViewModel: AddFoodViewModel
) {
    if (addFoodViewModel.isOpenAlert) {
        AlertDialog(
            onDismissRequest = { addFoodViewModel.closeAlertDialog() },
            text = {
                AlertDialogEmogiElement(addFoodViewModel = addFoodViewModel)
            },
            buttons = {},
            modifier = Modifier.fillMaxHeight(.8f)
        )
    }
}