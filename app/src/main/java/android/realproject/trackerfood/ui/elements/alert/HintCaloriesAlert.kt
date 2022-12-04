package android.realproject.trackerfood.ui.elements.alert

import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.AlertViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HintCaloriesAlert(
    alertViewModel: AlertViewModel,
    addFoodViewModel: AddFoodViewModel
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Небольшой список продуктов с их калорийностью",
            style = MaterialTheme.typography.h6,
            color = Color.White.copy(.8f),
            textAlign = TextAlign.Center
        )
        HintCaloriesElement(
            productName = "Burger",
            calories = 400,
            addFoodViewModel = addFoodViewModel,
            alertViewModel = alertViewModel
        )
        HintCaloriesElement(
            productName = "Lapha",
            calories = 300,
            addFoodViewModel = addFoodViewModel,
            alertViewModel = alertViewModel
        )
        HintCaloriesElement(
            productName = "Burger",
            calories = 500,
            addFoodViewModel = addFoodViewModel,
            alertViewModel = alertViewModel
        )
        HintCaloriesElement(
            productName = "Lapha",
            calories = 700,
            addFoodViewModel = addFoodViewModel,
            alertViewModel = alertViewModel
        )
    }
}

@Composable
private fun HintCaloriesElement(
    productName: String,
    calories: Int,
    addFoodViewModel: AddFoodViewModel,
    alertViewModel: AlertViewModel
){
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp). clickable {
            addFoodViewModel.setCountCalorieValue(calories.toString(), 0)
            alertViewModel.changeState(1)
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = productName, style = MaterialTheme.typography.h6, color = Color.Gray)
        Text(text = "$calories \uD83D\uDD25", style = MaterialTheme.typography.h6, color = Color.White)
    }
}