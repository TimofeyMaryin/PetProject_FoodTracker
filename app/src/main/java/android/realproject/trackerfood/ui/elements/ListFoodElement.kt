package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.model.date.Date
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListFoodElement(
    viewModel: MainViewModel,
    index: Int,
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 80.dp)
            .clickable {
                viewModel.changeIndexTouchProductIndex(index)
                       coroutineScope.launch {
                           if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                               bottomSheetScaffoldState.bottomSheetState.expand()
                           } else {
                               bottomSheetScaffoldState.bottomSheetState.collapse()
                           }
                       }
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(4f)
            ) {
                Text(
                    text = viewModel.getFoodByData(Date.getDayFood(Date.getCurrentDate()))[index].foodName,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${viewModel.getFoodByData(Date.getDayFood(Date.getCurrentDate()))[index].calories} калорий",
                    color = Color.Gray
                )
            }

            Text(
                text = viewModel.getFoodByData(Date.getDayFood(Date.getCurrentDate()))[index].time,
                style = MaterialTheme.typography.button,
                modifier = Modifier.weight(3f)
            )
            Text(
                text = viewModel.getFoodByData(Date.getDayFood(Date.getCurrentDate()))[index].emogi,
                style = MaterialTheme.typography.h3,
                modifier = Modifier.weight(2f)
            )

        }
    }
}

// Date.getHourMin(viewModel.getAllFood[index].data)