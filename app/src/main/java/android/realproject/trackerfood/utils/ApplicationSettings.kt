package android.realproject.trackerfood.utils

import android.realproject.trackerfood.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object ApplicationSettings {
    var bgColor: Any by mutableStateOf(R.color.black)
    var alphaElement: Float by mutableStateOf( 1f)

    var borderRadiusFloat by mutableStateOf(20f)
    var borderRadius by mutableStateOf(borderRadiusFloat.dp)

    var blackOutBg by mutableStateOf(.6f)



}