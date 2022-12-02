package android.realproject.trackerfood.model.navigation

sealed class Screen(val route: String) {
    object ListFoodScreen: Screen(route = "list_food")
    object AddFoodScreen: Screen(route = "add_food")
}
