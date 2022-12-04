package android.realproject.trackerfood.ui.elements

import android.realproject.trackerfood.data.viewModel.AddFoodViewModel
import android.realproject.trackerfood.model.navigation.Screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlin.random.Random

@Composable
fun ApplicationBottomBar(
    navController: NavController,
    modifier: Modifier,
    addFoodViewModel: AddFoodViewModel
) {
    var randIndex by remember { mutableStateOf(0) }


    val items = listOf(
        Screen.ListFoodScreen,
        Screen.AddFoodScreen,
        Screen.SettingScreen
    )

     BottomNavigation(
         modifier = Modifier.fillMaxWidth().then(modifier),
         backgroundColor = MaterialTheme.colors.background
     ) {
         val navBackStackEntry by navController.currentBackStackEntryAsState()
         val currentRoute = navBackStackEntry?.destination?.route

         items.forEach { item ->
             BottomNavigationItem(
                 icon = {
                        Icon(painter = painterResource(id = item.icon), contentDescription = item.desc)
                 },
                 label = { Text(text = item.desc)},
                 selected = currentRoute == item.route,
                 alwaysShowLabel = true,
                 selectedContentColor = Color.White,
                 unselectedContentColor = Color.White.copy(0.4f),
                 onClick = {
                     randIndex = Random.nextInt(from = 0,until = addFoodViewModel.listOfProductName.size)

                     if (item.route == Screen.AddFoodScreen.route) {

                         addFoodViewModel.generateRandomImage()
                         addFoodViewModel.generateRandomEmoji()
                         navController.navigate("${Screen.AddFoodScreen.route}/$randIndex")

                     } else {
                         navController.navigate(item.route) {
                             navController.graph.startDestinationRoute?.let {
                                 popUpTo(it) {
                                     saveState = true
                                 }
                             }
                             launchSingleTop = true
                             restoreState = true
                         }
                     }

                 })
         }
     }
}

