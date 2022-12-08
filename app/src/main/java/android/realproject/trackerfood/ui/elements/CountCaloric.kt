package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.model.date.Date
import android.realproject.trackerfood.ui.theme.GreenApp
import android.realproject.trackerfood.utils.ApplicationSettings
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@OptIn(ExperimentalFoundationApi::class, ExperimentalTextApi::class)
@Composable
fun CountCaloric(
    modifier: Modifier,
    viewModel: MainViewModel
) {
    val context = LocalContext.current

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
        val (content, hint) = createRefs()
        if (!viewModel.showOverview) {

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
                    Text(
                        text = "${
                            viewModel.calculateCaloric(
                                Date.getDayFood(Date.getCurrentDate()),
                                
                            )
                        }", style = MaterialTheme.typography.h4, color = Color.White
                    )
                    Text(
                        text = "Каллорий",
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.White
                    )
                }
            }
            Row(
                modifier = Modifier.constrainAs(hint) {
                    top.linkTo(parent.top, margin = 10.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "За день", color = Color.White)

            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { viewModel.showOverview = !viewModel.showOverview }
                    .constrainAs(content) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentAlignment = Alignment.Center,
            ){
                Text(
                    text = "Overview by day",
                    style = TextStyle(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color.White,
                                Color.Red
                            )
                        )
                    )
                )

            }
        }
    }
}



