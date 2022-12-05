package android.realproject.trackerfood.ui.elements.alert

import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.AlertViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun HintCaloriesAlert(
    alertViewModel: AlertViewModel,
    addFoodViewModel: AddFoodViewModel
){
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Небольшой список продуктов с их калорийностью",
                style = MaterialTheme.typography.h6,
                color = Color.White.copy(.8f),
                textAlign = TextAlign.Center
            )

        }
        items(addFoodViewModel.listOfProductWithCal.size) {
            HintCaloriesElement(

                addFoodViewModel = addFoodViewModel,
                alertViewModel = alertViewModel,
                index = it
            )
        }
        item {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
                    .border(BorderStroke(1.dp, Color.White.copy(.5f)), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White.copy(.5f)
                )
            }
        }

    }
}

@Composable
private fun HintCaloriesElement(
    addFoodViewModel: AddFoodViewModel,
    index: Int,
    alertViewModel: AlertViewModel
){
    val currentFoodInstance = addFoodViewModel.listOfProductWithCal[index]
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable {
                addFoodViewModel.setCountCalorieValue(currentFoodInstance.caloric.toString(), 0)
                addFoodViewModel.setCountCalorieValue(currentFoodInstance.name, 1)
                alertViewModel.changeState(1)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(3f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = currentFoodInstance.icon),
                contentDescription = null,
                modifier = Modifier.padding(end = 5.dp)
            )
            Text(
                text = currentFoodInstance.name,
                style = MaterialTheme.typography.subtitle2,
                color = Color.Gray,
            )

        }
        Text(
            text = "${currentFoodInstance.caloric} \uD83D\uDD25",
            style = MaterialTheme.typography.h6,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
    }
}