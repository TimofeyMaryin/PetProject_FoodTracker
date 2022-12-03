package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.R
import android.realproject.trackerfood.data.viewModel.AlertViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.model.navigation.Screen
import android.realproject.trackerfood.ui.elements.alert.ActionForAvatarsAlert
import android.realproject.trackerfood.ui.elements.alert.AlertContainer
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlin.random.Random

@Composable
fun AppTopBar(
    categoryText: String,
    modifier: Modifier,
    navController: NavController,
    viewModel: MainViewModel,
    alertViewModel: AlertViewModel
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .defaultMinSize(minHeight = 130.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val userAvatar by remember {
            mutableStateOf(
                if(viewModel.getAllAvatar().size != 0) {
                    viewModel.getAllAvatar().last().url
                } else {
                    "https://i.pinimg.com/564x/28/86/8c/28868c4b45558019fa6e3bafd0fc4c1f.jpg"
                }
            )
        }
        var randomInd by remember { mutableStateOf(0) }
        Column(
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = categoryText,
                style = MaterialTheme.typography.h4,
                color = Color.White.copy(.98f),
                fontWeight = FontWeight.Bold,
            )

            Log.e("AppTopBar", "RandomInd: $randomInd", )
            Text(
                text = viewModel.listOfMotivationPhrases[randomInd],
                style = MaterialTheme.typography.body2,
                color = Color.White.copy(.6f),
                modifier = Modifier.clickable {
                    randomInd = Random.nextInt(from = 0, until = viewModel.listOfMotivationPhrases.size)
                }
            )

        }
        IconButton(
            onClick = {
                //navController.navigate(Screen.SelectAvatarScreen.route)
                alertViewModel.changeState()
            },
            modifier = Modifier.weight(1f)
        ) {
            val defAvatar = "https://i.pinimg.com/564x/28/86/8c/28868c4b45558019fa6e3bafd0fc4c1f.jpg"
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = if(viewModel.checkAvailabilityAvatar()) defAvatar else userAvatar,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

        }
    }

    AlertContainer(
        contentAlert = {
            ActionForAvatarsAlert(viewModel = alertViewModel)
        },
        openDialog = alertViewModel.openActionMenuAvatar,
        onDismissRequest = { alertViewModel.changeState() }
    )
}