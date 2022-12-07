package android.realproject.trackerfood.data.viewModel.viewModelFactory

import android.content.SharedPreferences
import android.realproject.trackerfood.data.viewModel.SettingViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController

@Suppress("UNCHECKED_CAST")
class SettingViewModelFactory(
    private val navController: NavController,
    private val pref: SharedPreferences,
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingViewModel(navController = navController, pref) as T
    }
}