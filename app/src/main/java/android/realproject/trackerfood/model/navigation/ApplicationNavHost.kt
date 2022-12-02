package android.realproject.trackerfood.model.navigation

import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.ui.fragments.AddFoodFragment
import android.realproject.trackerfood.ui.fragments.ListFoodFragment
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ApplicationNavHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    addFoodViewModel: AddFoodViewModel
) {
    NavHost(navController = navController, startDestination = Screen.ListFoodScreen.route ) {
        composable(Screen.ListFoodScreen.route) {
            ListFoodFragment(
                viewModel = viewModel,
                navController = navController,
                addFoodViewModel = addFoodViewModel
            )
        }
        composable(Screen.AddFoodScreen.route) {
            AddFoodFragment(navController = navController, addFoodViewModel)
        }
    }
}