@file:Suppress("CAST_NEVER_SUCCEEDS")

package android.realproject.trackerfood.ui.elements.alert

import android.realproject.trackerfood.utils.ApplicationSettings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun SetBorderRadiusAlert(){
    val df = DecimalFormat("#")
    df.roundingMode = RoundingMode.CEILING

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Установи крутое закругление, шоб все дефки были твоими \uD83D\uDE0F",
            style = MaterialTheme.typography.h6
        )
        Text(
            text = df.format(ApplicationSettings.borderRadiusFloat).toString(),
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Slider(
            value = ApplicationSettings.borderRadiusFloat,
            onValueChange = {
                ApplicationSettings.borderRadiusFloat = it
                ApplicationSettings.borderRadius = ApplicationSettings.borderRadiusFloat.dp
            },
            steps = 34,
            valueRange = 1f..34f
        )

    }
}