package android.realproject.trackerfood.ui.fragments

import android.realproject.trackerfood.R
import android.realproject.trackerfood.data.db.FoodEntity
import android.realproject.trackerfood.data.db.food_hint_db.FoodHintEntity
import android.realproject.trackerfood.data.viewModel.AddFoodHintViewModel
import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.data.viewModel.MainViewModel
import android.realproject.trackerfood.model.navigation.Screen
import android.realproject.trackerfood.ui.elements.AppBottomBarAddFoodElement
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp

@Composable
fun AddFoodHintFragment(
    addFoodHintViewModel: AddFoodHintViewModel,
    navController: NavController,
    addFoodViewModel: AddFoodViewModel,
    mainViewModel: MainViewModel
){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        val (poleFillData, buttonConfirm, bg) = createRefs()

        PoleFillData(
            modifier = Modifier.constrainAs(poleFillData) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            viewModel = addFoodHintViewModel
        )

        AppBottomBarAddFoodElement(
            navController = navController,
            addFoodViewModel = addFoodViewModel,
            modifier = Modifier.constrainAs(buttonConfirm){
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            onClickCreateElement = {
                mainViewModel.insertProductHint(
                    FoodHintEntity(
                        productTitle = addFoodHintViewModel.foodTitle,
                        countCaloric = addFoodHintViewModel.foodCaloric.toInt(),
                        emogi = addFoodViewModel.emojiToFood
                    )
                )
                navController.popBackStack()
            }
        )

    }
}

@Composable
private fun PoleFillData(
    modifier: Modifier,
    viewModel: AddFoodHintViewModel
){
    Column(
        modifier = Modifier
            .fillMaxWidth(.9f)
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Container {
            HintTextField(
                value = viewModel.foodTitle,
                onChangeValue = { viewModel.changeValue(it, 0) },
                icon = R.drawable.ic_food,
                placeHolder = "Название продукта"
            )
        }
        Container {

            HintTextField(
                value = viewModel.foodCaloric,
                onChangeValue = { viewModel.changeValue(it, 1) },
                icon = R.drawable.ic_scale,
                placeHolder = "Количесво калорий за 100 грамм"
            )
        }

    }
}

@Composable
private fun HintTextField(
    value: String,
    onChangeValue: (String) -> Unit,
    icon: Int,
    placeHolder: String
) = TextField(
    value = value,
    onValueChange = { onChangeValue(it) },
    singleLine = true,
    leadingIcon = {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.White
        )
    },
    shape = RoundedCornerShape(30.dp),
    placeholder = {
        Text(text = placeHolder)
    },
    modifier = Modifier.fillMaxWidth(),
    colors = TextFieldDefaults.textFieldColors(
        disabledTextColor = Color.Transparent,
        backgroundColor = Color.White.copy(.7f),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
)

@Composable
private fun Container(
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.padding(vertical = 20.dp), contentAlignment = Alignment.Center){
        content()
    }
}