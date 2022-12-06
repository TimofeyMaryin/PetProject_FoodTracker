package android.realproject.trackerfood.model.navigation

import android.os.Build
import android.realproject.trackerfood.data.viewModel.*
import android.realproject.trackerfood.ui.fragments.*
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ApplicationNavHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    addFoodViewModel: AddFoodViewModel,
    selectImageViewModel: SelectImageViewModel,
    alertViewModel: AlertViewModel,
    settingViewModel: SettingViewModel,
    selectContentForBgViewModel: SelectContentForBgViewModel,
    addFoodHintViewModel: AddFoodHintViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ListFoodScreen.route
    ) {
        composable(Screen.ListFoodScreen.route) {
            ListFoodFragment(
                viewModel = viewModel,
                navController = navController,
                addFoodViewModel = addFoodViewModel,
                alertViewModel = alertViewModel
            )
        }
        composable("${Screen.AddFoodScreen.route}/{rand_index}") {
            val randIndex = it.arguments?.getString("rand_index", "3")
            AddFoodFragment(
                navController = navController,
                addFoodViewModel = addFoodViewModel,
                randomFoodIndex = randIndex!!.toInt(),
                alertViewModel = alertViewModel,
                mainViewModel = viewModel
            )
        }

        composable(Screen.SelectAvatarScreen.route) {
            SelectAvatarFragment(
                navController = navController,
                viewModel = selectImageViewModel
            )
        }

        composable(Screen.ShowAvatarCarouselScreen.route) {
            ShowAvatarFragment(mainViewModel = viewModel, navController = navController)
        }

        composable(Screen.SettingScreen.route) {
            SettingFragment(
                navController,
                addFoodViewModel,
                settingViewModel = settingViewModel,
                mainViewModel = viewModel
            )
        }

        composable(Screen.SelectBgScreen.route) {
            SelectBgFragment(navController = navController)
        }

        composable("${Screen.ChooseBgContent.route}/{type_content}") {
            val type = it.arguments?.getString("type_content", "0")
            ChooseBgContentFragment(
                type = type!!.toInt(),
                selectContentForBgViewModel,
                navController = navController
            )
        }

        composable(Screen.AddFoodHintScreen.route){
            AddFoodHintFragment(
                addFoodViewModel = addFoodViewModel,
                navController = navController,
                addFoodHintViewModel = addFoodHintViewModel,
                mainViewModel = viewModel
            )
        }
    }
}