package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.ui.theme.GreenApp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun CountCaloric(
    modifier: Modifier,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .fillMaxHeight(.4f)
            .clip(RoundedCornerShape(15.dp))
            .then(modifier)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "День", color = Color.White, modifier = Modifier.padding(horizontal = 20.dp))
            Text(text = "Неделя", color = Color.Gray, modifier = Modifier.padding(horizontal = 20.dp))
            Text(text = "Месяц", color = Color.Gray, modifier = Modifier.padding(horizontal = 20.dp))
            Text(text = "Год", color = Color.Gray, modifier = Modifier.padding(horizontal = 20.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(9f)
                .background(GreenApp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${viewModel.calculateCaloric()}", style = MaterialTheme.typography.h4, color = Color.White)
                Text(text = "Каллорий", style = MaterialTheme.typography.subtitle2, color = Color.White)
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