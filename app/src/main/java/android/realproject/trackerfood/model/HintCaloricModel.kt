package android.realproject.trackerfood.model

import android.service.autofill.OnClickAction

data class HintCaloricModel(
    val name: String,
    val onClickAction: () -> Unit,
)
