package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.data.db.FoodEntity
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.model.date.Date
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListFood(
    modifier: Modifier,
    viewModel: MainViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.Black)
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(viewModel.getAllFood.size) {

            Container {
                ListFoodElement(viewModel = viewModel, index = it)
            }
        }
    }
}


@Composable
private fun Container(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}