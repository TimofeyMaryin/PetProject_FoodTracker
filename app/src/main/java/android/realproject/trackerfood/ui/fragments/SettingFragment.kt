package android.realproject.trackerfood.ui.fragments

import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.data.viewModel.SettingViewModel
import android.realproject.trackerfood.ui.elements.*
import android.realproject.trackerfood.ui.elements.alert.AlertContainer
import android.realproject.trackerfood.ui.elements.alert.WarningAlert
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp

@Composable
fun SettingFragment(
    navController: NavController,
    addFoodViewModel: AddFoodViewModel,
    settingViewModel: SettingViewModel,
    mainViewModel: MainViewModel,
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (bottomBar, topBg, topBar, settingsItem, bgInfo) = createRefs()

        SetBg(modifier = Modifier.constrainAs(bgInfo) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        BackgroundIllustration(modifier = Modifier.constrainAs(topBg) {
            top.linkTo(parent.top, margin = (-10).dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        TopBarSetting(modifier = Modifier.constrainAs(topBar) {
            top.linkTo(parent.top)
            start.linkTo(parent.start, margin = 20.dp)
        }, mainViewModel = mainViewModel)


        SettingElement(settingViewModel = settingViewModel, modifier = Modifier.constrainAs(settingsItem) {
            bottom.linkTo(bottomBar.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        } )

        ApplicationBottomBar(
            navController = navController,
            modifier = Modifier.constrainAs(bottomBar) {
                bottom.linkTo(parent.bottom)
            },
            addFoodViewModel = addFoodViewModel
        )
    }

    AlertContainer(
        contentAlert = { WarningAlert(settingViewModel = settingViewModel) },
        openDialog = settingViewModel.userAgreeWarning
    ) {}


}

