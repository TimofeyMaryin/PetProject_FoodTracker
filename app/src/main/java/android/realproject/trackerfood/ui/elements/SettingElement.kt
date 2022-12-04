package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.R
import android.realproject.trackerfood.data.viewModel.SettingViewModel
import android.text.BoringLayout
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
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
            .fillMaxWidth(.9f)
            .background(Color.Gray)
            .clip(RoundedCornerShape(30.dp))
            .then(modifier),
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Text(
                text = "Настройки приложения",
                color = Color.Black,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(DEFAULT_PADDING)
            )
        }
        items(settingViewModel.listOfSettingsItemApplication.size){
            SettingElementItemApp(settingViewModel = settingViewModel, index = it)
        }

        item {
            Text(text = "Остальные настройки" , color = Color.Gray, fontWeight = FontWeight.Light)
        }
    }
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
            .padding(horizontal = DEFAULT_PADDING, vertical = 15.dp)
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
            modifier = Modifier.fillMaxWidth(.5f)
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