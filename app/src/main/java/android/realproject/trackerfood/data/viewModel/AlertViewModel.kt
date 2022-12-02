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
        changeState()
    }
    fun selectNewAvatar() {
        navController.navigate(Screen.SelectAvatarScreen.route)
        changeState()
    }
    fun deleteCurrentAvatar() {
        mainViewModel.deleteAvatar(mainViewModel.getAllAvatar.last())
        changeState()
    }
    fun deleteAllAvatar() {
        mainViewModel.deleteAllAvatar()
        changeState()
    }

    var openActionMenuAvatar by mutableStateOf(false)
        private set
    fun changeState() = run { openActionMenuAvatar = !openActionMenuAvatar }



}