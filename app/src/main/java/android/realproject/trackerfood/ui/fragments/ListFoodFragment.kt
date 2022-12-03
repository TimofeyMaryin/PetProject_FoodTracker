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
import android.realproject.trackerfood.ui.elements.AppTopBar
import android.realproject.trackerfood.ui.elements.CountCaloric
import android.realproject.trackerfood.ui.elements.FAB
import android.realproject.trackerfood.ui.elements.ListFood
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
        val (topBar, list, fab, countCal) = createRefs()

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
                bottom.linkTo(list.top)
            },
            viewModel,
        )
        ListFood(
            modifier = Modifier
                .fillMaxHeight(.4f)
                .constrainAs(list) {
                    top.linkTo(countCal.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            viewModel = viewModel,
        )
        var randIndex by remember { mutableStateOf(0) }
        val context = LocalContext.current

        FAB(
            modifier = Modifier.constrainAs(fab) {
                bottom.linkTo(parent.bottom, margin = 30.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            onClickUndoLong = {
                randIndex = Random.nextInt(from = 0,until = addFoodViewModel.listOfProductName.size)

                addFoodViewModel.generateRandomImage()
                addFoodViewModel.generateRandomEmoji()
                navController.navigate("${Screen.AddFoodScreen.route}/$randIndex")
            },
            onClickAfterLong = {
                addFoodViewModel.deleteDb()
            },
            onLongClick = {
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                val vibrationEffect: VibrationEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
                vibrator.cancel()
                vibrator.vibrate(vibrationEffect)
            }
        )
    }

}

