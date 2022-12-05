package android.realproject.trackerfood.data.viewModel

import android.realproject.trackerfood.model.SettingElementModel
import android.realproject.trackerfood.model.navigation.Screen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class SettingViewModel(
    private val navController: NavController
): ViewModel() {
    val listOfSettingsItemApplication = mutableListOf(
        SettingElementModel("Поменять фон", isCheckBox = false) { changeBg() },
        SettingElementModel("Установить время отправки уведомления", isCheckBox = false) {},
        SettingElementModel("Установить прозрачность", isCheckBox = false) { changeState(0) },
        SettingElementModel("Установить закругление сторон", isCheckBox = false) { changeState(1) },
        SettingElementModel("Затемнить фон", isCheckBox = false) {changeState(2)}
    )
    val listOfSettingsItemOther = mutableListOf(
        SettingElementModel("Авторы иллюстраций", isCheckBox = false) {},
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


}