package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.R
import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.model.date.Date
import android.realproject.trackerfood.ui.theme.GreenApp
import android.realproject.trackerfood.utils.ApplicationSettings
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CountCaloric(
    modifier: Modifier,
    viewModel: MainViewModel
) {

    val state = rememberLazyListState()
    val snappingLayout = remember(state) { SnapLayoutInfoProvider(state) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth(.95f)
            .fillMaxHeight(.3f)
            .border(
                BorderStroke(1.dp, Color.White),
                RoundedCornerShape(ApplicationSettings.borderRadius)
            )
            .clip(RoundedCornerShape(ApplicationSettings.borderRadius))
            .then(modifier)
    ) {
        val (caloricPager) = createRefs()
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(ApplicationSettings.alphaElement))
                .constrainAs(caloricPager) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            verticalAlignment = Alignment.CenterVertically,
            state = state,
            flingBehavior = flingBehavior
        ) {

            items(viewModel.sortCalByDayCount.size) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillParentMaxSize()
                        .background(GreenApp.copy(ApplicationSettings.alphaElement))
                ) {
                    val (hint, content) = createRefs()



                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(GreenApp.copy(ApplicationSettings.alphaElement))
                            .constrainAs(content) {
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "${viewModel.calculateCaloric(Date.getDayFood(Date.getCurrentDate()), it)}", style = MaterialTheme.typography.h4, color = Color.White)
                            Text(text = "Каллорий", style = MaterialTheme.typography.subtitle2, color = Color.White)
                        }
                    }
                    Row(
                        modifier = Modifier.constrainAs(hint){
                            top.linkTo(parent.top, margin = 10.dp)
                            end.linkTo(parent.end, margin = 10.dp)
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = viewModel.hintCaloricElement(it).name, color = Color.White)
                        IconButton(onClick = { viewModel.hintCaloricElement(it).onClickAction() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_graph),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }

            }
        }


    }
}


