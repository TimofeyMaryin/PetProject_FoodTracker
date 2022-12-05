package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.data.viewModel.MainViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
            .fillMaxHeight(.2f)
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
        val url = if(mainViewModel.getAllAvatar().size == 0) "https://i.pinimg.com/564x/28/86/8c/28868c4b45558019fa6e3bafd0fc4c1f.jpg" else mainViewModel.getAllAvatar().last().url
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier.size(100.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}