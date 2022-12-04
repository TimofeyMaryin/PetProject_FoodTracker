package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.data.viewModel.MainViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun TopBarSetting(
    modifier: Modifier,
    mainViewModel: MainViewModel
){
    Row(
        modifier = Modifier
            .fillMaxWidth(.9f)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Настройки",
            style = MaterialTheme.typography.h4,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )

        AsyncImage(
            model = mainViewModel.getAllAvatar().last().url, contentDescription = null,
            modifier = Modifier.size(100.dp).clip(CircleShape)
        )
    }
}