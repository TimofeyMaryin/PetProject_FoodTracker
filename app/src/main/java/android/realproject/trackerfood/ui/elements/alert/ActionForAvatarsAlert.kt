package android.realproject.trackerfood.ui.elements.alert

import android.realproject.trackerfood.R
import android.realproject.trackerfood.data.viewModel.AlertViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ActionForAvatarsAlert(
    viewModel: AlertViewModel
) {
    val defaultMargin = 0.dp
    val defaultHeight = 60.dp
    val paddingUndoIcon = 10.dp
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(defaultHeight)
                .clickable { viewModel.showAvatar() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_show_ava),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(end = paddingUndoIcon)
            )
            Text(text = "Посмотреть аватарку", modifier = Modifier.padding(start = defaultMargin))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(defaultHeight)
                .clickable { viewModel.selectNewAvatar() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_select_ava),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(end = paddingUndoIcon)
            )
            Text(text = "Поставить новую аватарку", modifier = Modifier.padding(start = defaultMargin))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(defaultHeight)
                .clickable { viewModel.deleteCurrentAvatar() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete_current_avatar),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(end = paddingUndoIcon)
            )
            Text(text = "Удалить нынешнию аватарку", modifier = Modifier.padding(start = defaultMargin))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(defaultHeight)
                .clickable { viewModel.deleteAllAvatar() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete_current_avatar),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(end = paddingUndoIcon)
            )
            Text(text = "Удалить все аватарки", modifier = Modifier.padding(start = defaultMargin))
        }
    }

}