package android.realproject.trackerfood.ui.fragments

import android.app.Fragment
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.AlertViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.model.date.Date
import android.realproject.trackerfood.model.navigation.Screen
import android.realproject.trackerfood.ui.elements.*
import android.realproject.trackerfood.utils.ApplicationSettings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ListFoodFragment(
    viewModel: MainViewModel,
    addFoodViewModel: AddFoodViewModel,
    navController: NavController,
    alertViewModel: AlertViewModel
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

