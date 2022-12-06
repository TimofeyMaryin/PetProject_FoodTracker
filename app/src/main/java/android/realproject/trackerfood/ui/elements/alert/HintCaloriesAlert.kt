package android.realproject.trackerfood.ui.elements.alert

import android.realproject.trackerfood.R
import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.AlertViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.model.navigation.Screen
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HintCaloriesAlert(
    alertViewModel: AlertViewModel,
    addFoodViewModel: AddFoodViewModel,
    navController: NavController,
    mainViewModel: MainViewModel
){
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Твой личный список, пользуйся",
                style = MaterialTheme.typography.h6,
                color = Color.White.copy(.8f),
                textAlign = TextAlign.Center
            )

        }
        items(mainViewModel.getAllHint().size) {
            HintCaloriesElement(
                addFoodViewModel = addFoodViewModel,
                mainViewModel = mainViewModel,
                alertViewModel = alertViewModel,
                index = it
            )
        }
        item {
            IconButton(
                onClick = { navController.navigate(Screen.AddFoodHintScreen.route) },
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HintCaloriesElement(
    mainViewModel: MainViewModel,
    index: Int,
    addFoodViewModel: AddFoodViewModel,
    alertViewModel: AlertViewModel

){
    val currentFoodInstance = mainViewModel.getAllHint()[index]
    val context = LocalContext.current
    var isDeleteElement by remember {mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .height(60.dp)
            .combinedClickable(
                onClick = {
                    addFoodViewModel.setCountCalorieValue(
                        currentFoodInstance.countCaloric.toString(),
                        0
                    )
                    addFoodViewModel.setCountCalorieValue(currentFoodInstance.productTitle, 1)
                    alertViewModel.changeState(1)
                    if (isDeleteElement) isDeleteElement = false
                },
                onLongClick = {
                    isDeleteElement = !isDeleteElement
                }

            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(3f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_food),
                contentDescription = null,
                modifier = Modifier.padding(end = 5.dp)
            )
            Text(
                text = mainViewModel.getAllHint()[index].productTitle,
                style = MaterialTheme.typography.subtitle2,
                color = Color.Gray,
            )

        }
        if(isDeleteElement){

            Box(
                modifier = Modifier.weight(1f).clickable { mainViewModel.deleteProductHint(mainViewModel.getAllHint()[index]) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                )
            }

        } else {
            Text(
                text = "${mainViewModel.getAllHint()[index].countCaloric} \uD83D\uDD25",
                style = MaterialTheme.typography.h6,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )

        }
    }
}