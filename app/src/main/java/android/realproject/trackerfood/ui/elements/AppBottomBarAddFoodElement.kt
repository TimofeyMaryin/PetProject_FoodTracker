package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.R
import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun AppBottomBarAddFoodElement(
    navController: NavController,
    addFoodViewModel: AddFoodViewModel,
    modifier: Modifier
){
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        BottomNavigationItem(
            selected = false,
            icon = { Icon(painter = painterResource(id = R.drawable.ic_bottom_bar_add_food_1), contentDescription = null) },
            label = { Text(text = "Отмена") },
            alwaysShowLabel = true,
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(0.4f),
            onClick = {
                navController.popBackStack()
            },

        )

        BottomNavigationItem(
            selected = false,
            icon = { Icon(painter = painterResource(id = R.drawable.ic_bottom_bar_add_food_2), contentDescription = null) },
            label = { Text(text = "Создать") },
            alwaysShowLabel = true,
            selectedContentColor = Color.White,
            unselectedContentColor = if (!addFoodViewModel.validateCreateFoodElement()) Color.White.copy(0.4f) else Color.White.copy(.8f),
            onClick = {
                if (addFoodViewModel.validateCreateFoodElement()) {
                    addFoodViewModel.createFoodElement()
                    navController.popBackStack()
                }
            }
        )
    }
}