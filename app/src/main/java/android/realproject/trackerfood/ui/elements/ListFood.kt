package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.data.db.FoodEntity
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.model.date.Date
import android.realproject.trackerfood.utils.ApplicationSettings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ListFood(
    modifier: Modifier,
    viewModel: MainViewModel
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .clip(RoundedCornerShape(ApplicationSettings.borderRadius))
            .background(Color.Black.copy(ApplicationSettings.alphaElement))
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.getFoodByData(Date.getCurrentDate()).size == 0) {
            items(viewModel.getFoodByData(Date.getDayFood(Date.getCurrentDate())).size) {
                Container {
                    ListFoodElement(viewModel = viewModel, index = it)
                }
            }
        } else {
            item {
                Box(
                    modifier = Modifier.alpha(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Добавь продукты", textAlign = TextAlign.Center)
                    }                    
                }
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