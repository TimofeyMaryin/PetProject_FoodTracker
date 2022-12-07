package android.realproject.trackerfood.ui.fragments

import android.realproject.trackerfood.data.viewModel.SelectContentForBgViewModel
import android.realproject.trackerfood.ui.elements.TopBarSelectFragment
import android.realproject.trackerfood.utils.ApplicationSettings
import android.realproject.trackerfood.utils.LIST_COLOR_BG
import android.realproject.trackerfood.utils.LIST_OF_IMAGE_BG
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun ChooseBgContentFragment(
    type: Int,
    selectContentForBgViewModel: SelectContentForBgViewModel,
    navController: NavController
) {
    when(type){
        0 -> { SelectColorFragment(selectContentForBgViewModel, navController)}
        1 -> { SelectImageFragment(selectContentForBgViewModel, navController) }
    }
}

@Composable
private fun SelectColorFragment(
    viewModel: SelectContentForBgViewModel,
    navController: NavController,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBarSelectFragment(
            modifier = Modifier.fillMaxWidth(),
            navController = navController,
            text = "Выбери цвет, дэб"
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(LIST_COLOR_BG.size) {
                Container {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(colorResource(id = LIST_COLOR_BG[it]))
                            .size(100.dp)
                            .clickable {
                                ApplicationSettings.bgColor = LIST_COLOR_BG[it]
                                viewModel.selectImageBg()
                            }
                    )

                }
            }
        }
    }

}

@Composable
private fun SelectImageFragment(
    viewModel: SelectContentForBgViewModel,
    navController: NavController,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBarSelectFragment(
            modifier = Modifier.fillMaxWidth(),
            navController = navController,
            text = "Выбери фоновую картинку, крутой поц"
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(LIST_OF_IMAGE_BG.size) {
                Container {
                    AsyncImage(
                        model =  LIST_OF_IMAGE_BG[it],
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .width(100.dp)
                            .height(160.dp)
                            .clickable {
                                ApplicationSettings.bgColor = LIST_OF_IMAGE_BG[it]
                                viewModel.selectImageBg()
                            }
                    )

                }
            }
        }
    }
}




@Composable
private fun Container(
    content: @Composable () -> Unit
){
    Box(
        modifier = Modifier.padding(10.dp),
        contentAlignment = Alignment.Center
    ){
        content()
    }
}

