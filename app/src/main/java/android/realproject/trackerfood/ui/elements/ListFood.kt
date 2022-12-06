package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.data.db.FoodEntity
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.model.date.Date
import android.realproject.trackerfood.model.navigation.Screen
import android.realproject.trackerfood.ui.theme.Purple500
import android.realproject.trackerfood.utils.ApplicationSettings
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalTextApi::class, ExperimentalMaterialApi::class)
@Composable
fun ListFood(
    modifier: Modifier,
    viewModel: MainViewModel,
    navController: NavController,
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .clip(RoundedCornerShape(ApplicationSettings.borderRadius))
            .border(
                BorderStroke(1.dp, Color.White),
                RoundedCornerShape(ApplicationSettings.borderRadius)
            )
            .background(Color.Black.copy(ApplicationSettings.alphaElement))
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.getFoodByData(Date.getDayFood(Date.getCurrentDate())).size != 0) {
            items(viewModel.getFoodByData(Date.getDayFood(Date.getCurrentDate())).size) {
                Container {
                    ListFoodElement(viewModel = viewModel, index = it, bottomSheetScaffoldState)
                }
            }
        } else {
            item {
                Box(
                    modifier = Modifier
                        .fillParentMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Добавить",
                        style = TextStyle(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color(0xFF3EADCF),
                                    Color(0xFFABE9CD)
                                )
                            ),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.clickable {
                            navController.navigate(
                                "${Screen.AddFoodScreen.route}/3",
                            )
                        }

                    )
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