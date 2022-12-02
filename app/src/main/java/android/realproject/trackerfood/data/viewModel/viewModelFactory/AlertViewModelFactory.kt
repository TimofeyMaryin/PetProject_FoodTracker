package android.realproject.trackerfood.data.viewModel.viewModelFactory

import android.realproject.trackerfood.data.viewModel.AlertViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController

@Suppress("UNCHECKED_CAST")
class AlertViewModelFactory(
    private val navController: NavController,
    private val mainViewModel: MainViewModel
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlertViewModel(navController, mainViewModel = mainViewModel) as T
    }
}