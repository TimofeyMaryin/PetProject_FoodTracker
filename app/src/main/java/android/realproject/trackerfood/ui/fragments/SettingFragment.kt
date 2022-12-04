package android.realproject.trackerfood.ui.fragments

import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.SettingViewModel
import android.realproject.trackerfood.ui.elements.ApplicationBottomBar
import android.realproject.trackerfood.ui.elements.BackgroundIllustration
import android.realproject.trackerfood.ui.elements.SettingElement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp

@Composable
fun SettingFragment(
    navController: NavController,
    addFoodViewModel: AddFoodViewModel,
    settingViewModel: SettingViewModel
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (bottomBar, topBg, topBar, settingsItem) = createRefs()

        Text(
            text = "Настройки",
            style = MaterialTheme.typography.h4,
            color = Color.White,
            fontWeight = Bold,
            modifier = Modifier.constrainAs(topBar) {
                top.linkTo(parent.top, margin = 20.dp)
                start.linkTo(parent.start, margin = 20.dp)
            }
        )

        BackgroundIllustration(modifier = Modifier.constrainAs(topBg) {
            top.linkTo(parent.top, margin = (-10).dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

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
}