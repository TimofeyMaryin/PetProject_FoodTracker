package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.model.date.Date
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ListFoodElement(
    viewModel: MainViewModel,
    index: Int,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 80.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Text(text = viewModel.getFoodByData(Date.getDayFood(Date.getCurrentDate()))[index].foodName, fontWeight = FontWeight.Bold)
                Text(text = "${viewModel.getFoodByData(Date.getDayFood(Date.getCurrentDate()))[index].calories} калорий", color = Color.Gray)
            }

            Text(text = viewModel.getFoodByData(Date.getDayFood(Date.getCurrentDate()))[index].time, style = MaterialTheme.typography.button)
            Text(text = viewModel.getFoodByData(Date.getDayFood(Date.getCurrentDate()))[index].emogi, style = MaterialTheme.typography.h3)

        }
    }
}

// Date.getHourMin(viewModel.getAllFood[index].data)