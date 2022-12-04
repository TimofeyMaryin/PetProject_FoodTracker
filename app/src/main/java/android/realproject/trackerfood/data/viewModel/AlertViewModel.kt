package android.realproject.trackerfood.data.viewModel

import android.realproject.trackerfood.model.navigation.Screen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class AlertViewModel(
    private val navController: NavController,
    private val mainViewModel: MainViewModel
):ViewModel() {
    fun showAvatar() {
        navController.navigate(Screen.ShowAvatarCarouselScreen.route)
        changeState(0)
    }
    fun selectNewAvatar() {
        navController.navigate(Screen.SelectAvatarScreen.route)
        changeState(0)
    }
    fun deleteCurrentAvatar() {
        mainViewModel.deleteAvatar(mainViewModel.getAllAvatar().last())
        changeState(0)
    }
    fun deleteAllAvatar() {
        mainViewModel.deleteAllAvatar()
        changeState(0)
    }

    var openActionMenuAvatar by mutableStateOf(false)
        private set
    fun changeState(index: Int) = run {
        when(index) {
            0 -> openActionMenuAvatar = !openActionMenuAvatar
            1 -> openActionHintCalories = !openActionHintCalories
        }
    }

    var openActionHintCalories by mutableStateOf(false)
        private set

}