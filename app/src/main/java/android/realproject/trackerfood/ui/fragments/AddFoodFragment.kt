package android.realproject.trackerfood.ui.fragments

import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.model.date.Date
import android.realproject.trackerfood.ui.elements.AddFoodEnterValue
import android.realproject.trackerfood.ui.elements.AppAlertDialog
import android.realproject.trackerfood.ui.elements.FAB
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.random.Random

@Composable
fun AddFoodFragment(
    navController: NavController,
    addFoodViewModel: AddFoodViewModel,
    randomFoodIndex: Int,
) {
    AppAlertDialog(addFoodViewModel = addFoodViewModel)
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (profile, fabContainer, addFoodElement, bg) = createRefs()
        ContainerBg(
            modifier = Modifier.constrainAs(bg) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            addFoodViewModel = addFoodViewModel,
        )

        AddFoodEnterValue(addFoodViewModel = addFoodViewModel, modifier = Modifier.constrainAs(addFoodElement){
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, randomFoodIndex = randomFoodIndex)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(fabContainer) {
                    bottom.linkTo(parent.bottom, margin = 30.dp)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            /** FAB BACK **/
            Container {
                FAB(
                    modifier = Modifier,
                    onClickAction = { navController.popBackStack() },
                    icon = Icons.Default.ArrowBack,
                    bgColor = Color.Red
                )
            }

            /** FAB DONE */
            Container {
                FAB(
                    modifier = Modifier,
                    onClickAction = {

                        addFoodViewModel.createFoodElement()
                        navController.popBackStack()

                        Log.e("AddFoodFragment", "currentDate: ${Date.getCurrentDate()}", )
                        Log.e("AddFoodFragment", "dayFood: ${Date.getDayFood(Date.getCurrentDate())}", )
                    },
                    icon = Icons.Default.Done,
                    bgColor = Color.Green
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
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun ContainerBg(
    modifier: Modifier,
    addFoodViewModel: AddFoodViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        contentAlignment = Alignment.TopCenter,
    ) {
        Image(
            painter = painterResource(
                id = addFoodViewModel.bgColor
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .blur(8.dp),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.Black.copy(.9f)
                )
        )
    }
}