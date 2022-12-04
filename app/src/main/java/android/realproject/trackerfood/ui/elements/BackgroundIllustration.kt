package android.realproject.trackerfood.ui.elements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color

@Composable
fun BackgroundIllustration(
    modifier: Modifier
) {
    Canvas(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(.3f).then(modifier),
    ) {
        drawRoundRect(
            color = Color.Blue,
            cornerRadius = CornerRadius(70f, 70f)
        )
    }
}