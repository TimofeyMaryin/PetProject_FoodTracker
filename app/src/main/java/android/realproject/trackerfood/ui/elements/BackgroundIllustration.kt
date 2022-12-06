package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.ui.theme.GreenApp
import android.realproject.trackerfood.utils.ApplicationSettings
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BackgroundIllustration(
    modifier: Modifier
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .clip(RoundedCornerShape(bottomStart = ApplicationSettings.borderRadius, bottomEnd = ApplicationSettings.borderRadius))
            .fillMaxHeight(.2f)
            .background(GreenApp)
            .then(modifier),
    ) {

        drawCircle(
            color = Color.Green.copy(.3f),
            radius = 45f,
            center = Offset(100f, 170f)
        )
        drawCircle(
            color = Color.Green.copy(.3f),
            radius = 45f,
            center = Offset(250f, 130f)
        )
        drawCircle(
            color = Color.Green.copy(.3f),
            radius = 65f,
            center = Offset(230f, 300f)
        )
        drawCircle(
            color = Color.Green.copy(.3f),
            radius = 25f,
            center = Offset(190f, 320f)
        )

        drawCircle(
            color = Color.Green.copy(.3f),
            radius = 25f,
            center = Offset(490f, 290f)
        )
        drawCircle(
            color = Color.Green.copy(.3f),
            radius = 25f,
            center = Offset(400f, 130f)
        )
        drawCircle(
            color = Color.Green.copy(.3f),
            radius = 25f,
            center = Offset(340f, 300f)
        )
    }
}