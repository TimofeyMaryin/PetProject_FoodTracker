package android.realproject.trackerfood.data.viewModel

import android.content.SharedPreferences
import android.realproject.trackerfood.model.SettingElementModel
import android.realproject.trackerfood.model.navigation.Screen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class SettingViewModel(
    private val navController: NavController,
    private val pref: SharedPreferences
): ViewModel() {
    val listOfSettingsItemApplication = mutableListOf(
        SettingElementModel("Поменять фон", isCheckBox = false) { changeBg() },
        SettingElementModel("Установить прозрачность", isCheckBox = false) { changeState(0) },
        SettingElementModel("Установить закругление сторон", isCheckBox = false) { changeState(1) },
        SettingElementModel("Затемнить фон", isCheckBox = false) {changeState(2)}
    )


    private fun changeBg(){
        navController.navigate(Screen.SelectBgScreen.route)
    }

    var sliderAlertDialogState by mutableStateOf(false)
    var setBorderRadiusAlert by mutableStateOf(false)
    var setBlackoutToBG by mutableStateOf(false)
    fun changeState(index: Int) = run {
        when (index){
            0 -> sliderAlertDialogState = !sliderAlertDialogState
            1 -> setBorderRadiusAlert = !setBorderRadiusAlert
            2 -> setBlackoutToBG = !setBlackoutToBG
        }
    }

    var userAgreeWarning by mutableStateOf(true)
    var userAgree by mutableStateOf(false)
    fun userMustBeAgree() = run { userAgreeWarning = !userAgreeWarning }
}