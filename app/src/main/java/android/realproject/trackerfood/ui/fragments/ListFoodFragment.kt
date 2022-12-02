package android.realproject.trackerfood.ui.fragments

import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.model.date.Date
import android.realproject.trackerfood.model.navigation.Screen
import android.realproject.trackerfood.ui.elements.AppTopBar
import android.realproject.trackerfood.ui.elements.CountCaloric
import android.realproject.trackerfood.ui.elements.FAB
import android.realproject.trackerfood.ui.elements.ListFood
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ListFoodFragment(
    viewModel: MainViewModel,
    addFoodViewModel: AddFoodViewModel,
    navController: NavController
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

        FAB(
            modifier = Modifier.constrainAs(fab) {
                bottom.linkTo(parent.bottom, margin = 30.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            onClickUndoLong = {
                addFoodViewModel.generateRandomImage()
                addFoodViewModel.generateRandomEmoji()
                navController.navigate(Screen.AddFoodScreen.route)
            },
            onClickAfterLong = {
                addFoodViewModel.deleteDb()
            }
        )
    }

}