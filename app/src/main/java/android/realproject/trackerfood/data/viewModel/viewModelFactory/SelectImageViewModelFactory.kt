package android.realproject.trackerfood.data.viewModel.viewModelFactory

import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.data.viewModel.SelectImageViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class SelectImageViewModelFactory(
    private val mainViewModel: MainViewModel
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectImageViewModel(mainViewModel) as T
    }
}