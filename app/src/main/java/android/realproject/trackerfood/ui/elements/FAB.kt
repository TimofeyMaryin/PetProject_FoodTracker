package android.realproject.trackerfood.ui.elements

import android.media.Image
import android.service.autofill.OnClickAction
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun FAB(
    modifier: Modifier,
    onClickAction: () -> Unit,
    icon: ImageVector = Icons.Default.Add,
    tint: Color = Color.White,
    bgColor: Color = Color.Gray,

) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(50.dp)
            .background(bgColor)
            .clickable { onClickAction() }
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = tint)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FAB(
    modifier: Modifier,

    onClickUndoLong: () -> Unit,
    onClickAfterLong: () -> Unit,
    onLongClick: () -> Unit

) {

    var isLongTouched by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(50.dp)
            .background(if(isLongTouched) Color.Red else Color.Gray)
            .combinedClickable(
                onLongClick = {
                    onLongClick()
                    isLongTouched = !isLongTouched
                },
                onClick = {
                    if (isLongTouched) {
                        onClickAfterLong()
                    } else {
                        onClickUndoLong()
                    }
                }
            )
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = if (isLongTouched) Icons.Default.Delete else Icons.Default.Add,
            contentDescription = null,
            tint = Color.White
        )
    }
}