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

        // AlertImage with slider
        SettingElementModel("Установить прозрачность", isCheckBox = false) { changeState() }
    )
    val listOfSettingsItemOther = mutableListOf(
        SettingElementModel("Авторы иллюстраций", isCheckBox = false) {},
    )

    private fun changeBg(){
        navController.navigate(Screen.SelectBgScreen.route)
    }

    var sliderAlertDialogState by mutableStateOf(false)
    fun changeState() = run { sliderAlertDialogState = !sliderAlertDialogState }


}