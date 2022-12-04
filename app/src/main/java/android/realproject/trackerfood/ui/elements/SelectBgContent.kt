package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.R
import android.realproject.trackerfood.model.navigation.Screen
import android.service.autofill.OnClickAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun SelectBgContent(
    modifier: Modifier,
    navController: NavController
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Container(modifier = Modifier.weight(1f)) {
            SelectBgItem(
                modifier = Modifier,
                onClickAction = { navController.navigate("${Screen.ChooseBgContent.route}/0") },
                image = R.drawable.palitra,
                "Цвета"
            )
        }
        Container(modifier = Modifier.weight(1f)) {
            SelectBgItem(
                modifier = Modifier,
                onClickAction = { navController.navigate("${Screen.ChooseBgContent.route}/1") },
                image = R.drawable.collage,
                "Фото"
            )
        }
    }

}


@Composable
private fun SelectBgItem(
    modifier: Modifier,
    onClickAction: () -> Unit,
    image: Int,
    type: String
){
    Column(
        modifier = Modifier
            .fillMaxHeight(.24f)
            .clickable { onClickAction() }
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier.weight(5f).clip(RoundedCornerShape(30.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = type,
            color = Color.White.copy(.4f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.weight(1f)
        )

    }
}

@Composable
private fun Container(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier
        .padding(30.dp)
        .then(modifier), contentAlignment = Alignment.Center) {
        content()
    }
}