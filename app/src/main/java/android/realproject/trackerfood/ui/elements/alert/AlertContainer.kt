package android.realproject.trackerfood.ui.elements.alert

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun AlertContainer(
    contentAlert: @Composable () -> Unit,
    openDialog: Boolean,
    onDismissRequest: () -> Unit
) {

    if (openDialog) {

        AlertDialog(
            onDismissRequest = { onDismissRequest() },
            text = { contentAlert() },
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)),
            buttons = {}
        )

    }

}