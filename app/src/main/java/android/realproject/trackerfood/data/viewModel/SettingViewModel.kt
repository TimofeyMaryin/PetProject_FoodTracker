package android.realproject.trackerfood.data.viewModel

import android.realproject.trackerfood.model.SettingElementModel
import androidx.lifecycle.ViewModel

class SettingViewModel: ViewModel() {
    val listOfSettingsItemApplication = mutableListOf(
        SettingElementModel("Включить уведомления", isCheckBox = true, {}),
        SettingElementModel("Поменять фон", isCheckBox = false, {}),
        SettingElementModel("Установить время отправки уведомления", isCheckBox = false, {}),
    )
    val listOfSettingsItemOther = mutableListOf(
        SettingElementModel("Авторы иллюстраций", isCheckBox = false, {}),
    )
}