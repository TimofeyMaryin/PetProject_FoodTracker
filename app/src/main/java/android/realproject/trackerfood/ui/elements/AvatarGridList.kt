package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.data.viewModel.SelectImageViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun AvatarGridList(
    viewModel: SelectImageViewModel,
    modifier: Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        items(viewModel.imageUrl.size) {
            Container {
                AsyncImage(
                    model = viewModel.imageUrl[it].value.url,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { viewModel.selectImage(it) }
                        .clip(RoundedCornerShape(14.dp))
                        .size(140.dp)
                        .border(BorderStroke(1.dp, Color.White), RoundedCornerShape(14.dp)),
                    contentScale = ContentScale.Crop
                )

            }

        }
    }
}


@Composable
private fun Container(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .padding(20.dp),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}
