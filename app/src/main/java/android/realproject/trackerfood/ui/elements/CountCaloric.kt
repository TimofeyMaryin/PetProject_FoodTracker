package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.model.date.Date
import android.realproject.trackerfood.ui.theme.GreenApp
import android.realproject.trackerfood.utils.ApplicationSettings
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CountCaloric(
    modifier: Modifier,
    viewModel: MainViewModel
) {
    var indexDay by remember {
        mutableStateOf(0)
    }
    val state = rememberLazyListState()
    val snappingLayout = remember(state) { SnapLayoutInfoProvider(state) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    Column(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .fillMaxHeight(.34f)
            .clip(RoundedCornerShape(ApplicationSettings.borderRadius))
            .then(modifier)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(ApplicationSettings.alphaElement))
                .weight(2f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 0 until viewModel.sortCalByDayCount.size) {
                Text(
                    text = viewModel.sortCalByDayCount[i].value.title,
                    color = if(indexDay == i) Color.White else Color.Gray,
                    modifier = Modifier.padding(horizontal = 20.dp)

                )

            }

        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .weight(9f)
                .background(Color.Black.copy(ApplicationSettings.alphaElement)),
            verticalAlignment = Alignment.CenterVertically,
            state = state,
            flingBehavior = flingBehavior
        ) {

            items(viewModel.sortCalByDayCount.size) {
                indexDay = it
                Box(
                    modifier = Modifier
                        .fillParentMaxSize()
                        .background(GreenApp.copy(ApplicationSettings.alphaElement)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "${viewModel.calculateCaloric(Date.getDayFood(Date.getCurrentDate()), it)}", style = MaterialTheme.typography.h4, color = Color.White)
                        Text(text = "Каллорий", style = MaterialTheme.typography.subtitle2, color = Color.White)
                    }
                }
            }
        }


    }
}

@Composable
private fun CategoryElement(
    countCal: Int,
    compleatedFloat: Float
) {
    Column() {
        Text(
            text = "$countCal",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6
        )
        AppProgressBar(completeFloat = compleatedFloat)
        Text(text = "caloric")
    }
}

@Composable
private fun AppProgressBar(
    completeFloat: Float
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .clip(CircleShape)
            .background(Color.Gray.copy(.6f)),
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(modifier = Modifier
            .fillMaxWidth(completeFloat)
            .height(4.dp)
            .background(Color.Yellow))
    }
}