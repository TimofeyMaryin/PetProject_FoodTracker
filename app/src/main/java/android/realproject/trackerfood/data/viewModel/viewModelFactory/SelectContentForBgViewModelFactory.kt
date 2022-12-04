package android.realproject.trackerfood.data.viewModel.viewModelFactory

import android.content.Context
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.data.viewModel.SelectContentForBgViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController

@Suppress("UNCHECKED_CAST")
class SelectContentForBgViewModelFactory(
    private val navController: NavController,
    private val mainViewModel: MainViewModel,
    private val context: Context
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectContentForBgViewModel(navController, mainViewModel, context) as T
    }
}