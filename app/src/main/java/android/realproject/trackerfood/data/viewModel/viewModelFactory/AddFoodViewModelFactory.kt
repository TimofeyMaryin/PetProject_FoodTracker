package android.realproject.trackerfood.data.viewModel.viewModelFactory

import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class AddFoodViewModelFactory(
    private val mainViewModel: MainViewModel
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddFoodViewModel(mainViewModel) as T
    }
}