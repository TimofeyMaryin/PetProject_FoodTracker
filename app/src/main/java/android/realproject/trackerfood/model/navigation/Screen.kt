package android.realproject.trackerfood.model.navigation

sealed class Screen(val route: String) {
    object ListFoodScreen: Screen(route = "list_food")
    object AddFoodScreen: Screen(route = "add_food")
    object SelectAvatarScreen: Screen(route = "select_avatar")
    object ShowAvatarCarouselScreen: Screen(route = "show_avatar")
}
