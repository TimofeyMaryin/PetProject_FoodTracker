package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.utils.ApplicationSettings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import coil.compose.AsyncImage

@Composable
fun SetBg(
    modifier: Modifier
){
    if (ApplicationSettings.bgColor is Int) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier),
        ){
            Box(modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = ApplicationSettings.bgColor as Int))
                    then(modifier)
            )

            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(ApplicationSettings.blackOutBg)))
        }

    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier),
        ) {
            AsyncImage(
                model = ApplicationSettings.bgColor,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(ApplicationSettings.blackOutBg))
            )

        }
    }
}