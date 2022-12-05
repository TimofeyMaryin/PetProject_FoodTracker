package android.realproject.trackerfood.ui.elements.alert

import android.realproject.trackerfood.utils.ApplicationSettings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun SettingAlert() {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Давай установим тебе крутуя прозрачность \uD83D\uDE0F",
            style = MaterialTheme.typography.h6
        )
        Text(
            text = df.format(ApplicationSettings.alphaElement).toString(),
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Slider(
            value = ApplicationSettings.alphaElement, onValueChange = {ApplicationSettings.alphaElement = it},
            steps = 10,
            valueRange = .4f..1f
        )

    }
}