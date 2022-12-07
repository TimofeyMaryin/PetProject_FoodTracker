package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.R
import android.realproject.trackerfood.data.viewModel.SettingViewModel
import android.realproject.trackerfood.ui.elements.alert.AlertContainer
import android.realproject.trackerfood.ui.elements.alert.SetBlackOut
import android.realproject.trackerfood.ui.elements.alert.SetBorderRadiusAlert
import android.realproject.trackerfood.ui.elements.alert.SettingAlert
import android.realproject.trackerfood.utils.ApplicationSettings
import android.text.BoringLayout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


val DEFAULT_PADDING = 20.dp
val SMALL_PADDING = 10.dp

@Composable
fun SettingElement(
    settingViewModel: SettingViewModel,
    modifier: Modifier
) {

    LazyColumn(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = ApplicationSettings.borderRadius,
                    topEnd = ApplicationSettings.borderRadius
                )
            )
            .fillMaxWidth(.95f)
            .fillMaxHeight(.7f)
            .border(
                BorderStroke(1.dp, brush = Brush.verticalGradient(
                    .0f to Color.White,
                    1f to Color.Transparent
                )),
                RoundedCornerShape(topStart = ApplicationSettings.borderRadius, topEnd = ApplicationSettings.borderRadius)
            )
            .background(Color.Black.copy(ApplicationSettings.alphaElement))
            .then(modifier),
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Text(
                text = "Настройки приложения",
                color = Color.White.copy(.6f),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = DEFAULT_PADDING, vertical = DEFAULT_PADDING+10.dp)
            )
        }
        items(settingViewModel.listOfSettingsItemApplication.size){
            SettingElementItemApp(settingViewModel = settingViewModel, index = it)
        }

    }

    AlertContainer(
        contentAlert = { SettingAlert() },
        openDialog = settingViewModel.sliderAlertDialogState,
        onDismissRequest = { settingViewModel.changeState(0) }
    )

    AlertContainer(
        contentAlert = { SetBorderRadiusAlert() },
        openDialog = settingViewModel.setBorderRadiusAlert,
        onDismissRequest = { settingViewModel.changeState(1) }
    )
    AlertContainer(
        contentAlert = { SetBlackOut() },
        openDialog = settingViewModel.setBlackoutToBG,
        onDismissRequest = { settingViewModel.changeState(2) }
    )
}


@Composable
private fun SettingElementItemApp(
    settingViewModel: SettingViewModel,
    index: Int
) {
    val currentElement = settingViewModel.listOfSettingsItemApplication[index]
    var checked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(horizontal = DEFAULT_PADDING, vertical = DEFAULT_PADDING)
            .fillMaxWidth()
            .clickable {
                if (!currentElement.isCheckBox) {
                    currentElement.onClickAction()
                } else {
                    checked = !checked
                }
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = currentElement.title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(.7f),
            overflow = TextOverflow.Ellipsis
        )
        if (currentElement.isCheckBox) {
            Checkbox(
                checked = checked, onCheckedChange = {},
                modifier = Modifier.padding(vertical = SMALL_PADDING)
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_forward_line),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(vertical = SMALL_PADDING)

            )
        }
    }
}