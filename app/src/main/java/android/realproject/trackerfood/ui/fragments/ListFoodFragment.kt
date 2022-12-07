package android.realproject.trackerfood.ui.fragments

import android.app.Fragment
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.realproject.trackerfood.R
import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.AlertViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.model.date.Date
import android.realproject.trackerfood.model.navigation.Screen
import android.realproject.trackerfood.ui.elements.*
import android.realproject.trackerfood.utils.ApplicationSettings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ListFoodFragment(
    viewModel: MainViewModel,
    addFoodViewModel: AddFoodViewModel,
    navController: NavController,
    alertViewModel: AlertViewModel
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val textSizeBottomSheet = 22.sp
    val sizeIcon = 40.dp
    val padding = 10.dp
    val colorElement = Color.White.copy(.4f)
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            val currentFoodElement =
                if (
                    viewModel.getFoodByData(Date.getDayFood(Date.getCurrentDate())).size != 0
                )
                    viewModel.getFoodByData(Date.getDayFood(Date.getCurrentDate()))[viewModel.indexTouchProductIndex]
                else null

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.6f)
            ) {
                val (content, upLine,delButton) = createRefs()
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .fillMaxWidth(.2f)
                        .height(3.dp)
                        .background(
                            Color.Black.copy(.5f),
                        )
                        .constrainAs(upLine) {
                            top.linkTo(parent.top, margin = 13.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                )
                if(currentFoodElement != null){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = padding)
                            .constrainAs(content) {
                                top.linkTo(upLine.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = padding)
                                .height(50.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {

                            Text(
                                text = currentFoodElement.foodName,
                                fontSize = 26.sp,
                                color = Color.White
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = padding)
                                .height(50.dp),
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_scale),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(sizeIcon)
                                    .padding(end = padding),
                                tint = colorElement
                            )
                            Text(
                                text = currentFoodElement.calories.toString(),
                                fontSize = textSizeBottomSheet,
                                color = colorElement
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = padding)
                                .height(50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_clock),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(sizeIcon)
                                    .padding(end = padding),
                                tint = colorElement
                            )
                            Text(
                                text = currentFoodElement.time,
                                fontSize = textSizeBottomSheet,
                                color = colorElement
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = padding)
                                .height(50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(sizeIcon)
                                    .padding(end = padding),
                                tint = colorElement
                            )
                            Text(text = currentFoodElement.emogi, fontSize = textSizeBottomSheet, color = colorElement)
                        }
                    }

                }

            }

        }, sheetPeekHeight = 0.dp
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (topBar, list, countCal, bottomBar, bgInfo) = createRefs()

            SetBg(modifier = Modifier.constrainAs(bgInfo) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

            AppTopBar(
                categoryText = Date.getDateToday(),
                modifier = Modifier.constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                navController = navController,
                viewModel = viewModel,
                alertViewModel = alertViewModel
            )
            CountCaloric(
                modifier = Modifier.constrainAs(countCal) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(list.top, margin = 5.dp)
                },
                viewModel,
            )
            ListFood(
                modifier = Modifier
                    .fillMaxHeight(.4f)
                    .constrainAs(list) {
                        top.linkTo(countCal.bottom)
                        bottom.linkTo(bottomBar.top, margin = 2.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                viewModel = viewModel,
                navController = navController,
                bottomSheetScaffoldState = bottomSheetScaffoldState
            )




            ApplicationBottomBar(
                navController = navController,
                modifier = Modifier.constrainAs(bottomBar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, addFoodViewModel)


        }

    }

}

