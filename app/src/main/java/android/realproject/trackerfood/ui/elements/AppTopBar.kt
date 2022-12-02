package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AppTopBar(
    categoryText: String,
    modifier: Modifier
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .defaultMinSize(minHeight = 130.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = categoryText,
            style = MaterialTheme.typography.h4,
            color = Color.White.copy(.98f),
            fontWeight = FontWeight.Bold,
        )
        IconButton(onClick = { /*TODO*/ }) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier.size(70.dp).clip(CircleShape)
            )
        }
    }

}