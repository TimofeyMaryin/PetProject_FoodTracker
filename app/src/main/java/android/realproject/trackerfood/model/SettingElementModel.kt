package android.realproject.trackerfood.model

import android.service.autofill.OnClickAction

data class SettingElementModel(
    val title: String,
    val isCheckBox: Boolean = false,
    val onClickAction: () -> Unit,
)
