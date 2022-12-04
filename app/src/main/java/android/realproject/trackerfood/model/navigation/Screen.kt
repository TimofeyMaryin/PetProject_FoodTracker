package android.realproject.trackerfood.model.navigation

import android.realproject.trackerfood.R

sealed class Screen(val route: String, val icon: Int = -1, val desc: String = "") {
    object ListFoodScreen: Screen(route = "list_food", icon = R.drawable.ic_bottom_icon_1, desc = " Главная")
    object AddFoodScreen: Screen(route = "add_food", icon = R.drawable.ic_bottom_icon_2 , desc = "Добавить")
    object SelectAvatarScreen: Screen(route = "select_avatar")
    object ShowAvatarCarouselScreen: Screen(route = "show_avatar")
    object SettingScreen: Screen(route = "setting_screen",  icon = R.drawable.ic_bottom_icon_3, desc = "Настройка")
    object SelectBgScreen: Screen(route = "select_bg")
    object ChooseBgContent: Screen("choose_bg_content")
}
