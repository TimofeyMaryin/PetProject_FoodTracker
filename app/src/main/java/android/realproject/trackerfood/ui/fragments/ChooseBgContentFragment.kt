package android.realproject.trackerfood.ui.fragments

import android.realproject.trackerfood.data.viewModel.SelectContentForBgViewModel
import android.realproject.trackerfood.ui.elements.TopBarSelectFragment
import android.realproject.trackerfood.utils.ApplicationSettings
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
            items(viewModel.listColorBg.size) {
                Container {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(colorResource(id = viewModel.listColorBg[it]))
                            .size(100.dp)
                            .clickable {
                                ApplicationSettings.bgColor = viewModel.listColorBg[it]
                                viewModel.selectImageBg(viewModel.listImageUrl[it])
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
            items(viewModel.listImageUrl.size) {
                Container {
                    AsyncImage(
                        model =  viewModel.listImageUrl[it],
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .width(100.dp)
                            .height(160.dp)
                            .clickable {
                                ApplicationSettings.bgColor = viewModel.listImageUrl[it]
                                viewModel.selectImageBg(viewModel.listImageUrl[it])
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

