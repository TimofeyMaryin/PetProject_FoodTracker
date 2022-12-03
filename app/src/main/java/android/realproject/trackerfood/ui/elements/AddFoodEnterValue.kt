package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Random
import kotlin.random.Random.Default.nextInt

@Composable
fun AddFoodEnterValue(
    addFoodViewModel: AddFoodViewModel,
    modifier: Modifier,
    randomFoodIndex: Int
) {

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .fillMaxWidth()
            .fillMaxHeight(.7f)
            .background(Color.Black.copy(.8f))
            .then(modifier),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AppTextField(
                value = addFoodViewModel.foodName,
                onChangeValue = { addFoodViewModel.setCountCalorieValue(it, 1) },
                placeholder = addFoodViewModel.listOfProductName[randomFoodIndex],
                countCal = addFoodViewModel.calculateCal(),
                selectCalInfo = true,
                keyboardType = KeyboardType.Text
            )
            AppTextField(
                value = addFoodViewModel.calories,
                onChangeValue = { addFoodViewModel.setCountCalorieValue(it, 0) },
                placeholder = "Укажите кол-во каллорий за 100 грамм",
                countCal = addFoodViewModel.calories
            )
            AppTextField(
                value = addFoodViewModel.weight,
                onChangeValue = { addFoodViewModel.setCountCalorieValue(it, 2) },
                placeholder = "Вес продукта",
                countCal = addFoodViewModel.calculateCal()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .clickable { addFoodViewModel.openAlertDialog() }
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.radialGradient(
                                listOf(
                                    Color(0xFF0D324D),
                                    Color(0xFF7F5A83)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = addFoodViewModel.emojiToFood,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    text = "Выбрать иконку",
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier
                        .padding(horizontal = 15.dp),
                    color = Color.Blue
                )
            }
        }
    }
}


@Composable
private fun AppTextField(
    value: String,
    onChangeValue: (String) -> Unit,
    placeholder: String,
    countCal: String,
    selectCalInfo: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Number
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = value,
            onValueChange = { onChangeValue(it) },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            placeholder = { Text(text = placeholder, maxLines = 1, overflow = TextOverflow.Ellipsis) },
            trailingIcon = {
                if (selectCalInfo) {
                    Text(text = "$countCal cal")
                }
            },
            leadingIcon = {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(BorderStroke(1.dp, Color.White), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            },
            shape = RoundedCornerShape(0),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White)
                .padding(top = 5.dp)
        )

    }
}