package android.realproject.trackerfood.model.navigation

import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.AlertViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.data.viewModel.SelectImageViewModel
import android.realproject.trackerfood.ui.fragments.AddFoodFragment
import android.realproject.trackerfood.ui.fragments.ListFoodFragment
import android.realproject.trackerfood.ui.fragments.SelectAvatarFragment
import android.realproject.trackerfood.ui.fragments.ShowAvatarFragment
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ApplicationNavHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    addFoodViewModel: AddFoodViewModel,
    selectImageViewModel: SelectImageViewModel,
    alertViewModel: AlertViewModel
) {
    NavHost(navController = navController, startDestination = Screen.ListFoodScreen.route ) {
        composable(Screen.ListFoodScreen.route) {
            ListFoodFragment(
                viewModel = viewModel,
                navController = navController,
                addFoodViewModel = addFoodViewModel,
                alertViewModel = alertViewModel
            )
        }
        composable(Screen.AddFoodScreen.route) {
            AddFoodFragment(
                navController = navController,
                addFoodViewModel = addFoodViewModel
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
    }
}