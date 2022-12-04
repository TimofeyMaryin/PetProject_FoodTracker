package android.realproject.trackerfood.data.viewModel

import android.realproject.trackerfood.R
import android.realproject.trackerfood.data.db.FoodEntity
import android.realproject.trackerfood.model.date.Date
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat


class AddFoodViewModel(
    private val viewModel: MainViewModel
): ViewModel() {

    var calories by mutableStateOf("")
        private set
    private var resCal by mutableStateOf(0f)
    var foodName by mutableStateOf("")
    var weight by mutableStateOf("")
    fun setCountCalorieValue(value: String, index: Int) = run {

        when (index) {
            0 -> if (validateEnterValue(value)) calories = value
            1 -> foodName = value
            2 ->  {
                if (validateEnterValue(value)) weight = value
            }
        }
    }
    private fun validateEnterValue(value: String): Boolean = value.isDigitsOnly()

    fun calculateCal(): String {
        var res = 0.0
        if (
            weight.isNotEmpty() && calories.isNotEmpty() &&
            weight.isDigitsOnly() && calories.isDigitsOnly()
        ) {
            resCal = calories.toFloat() * (weight.toFloat() / 100)
            res = calories.toDouble() * (weight.toDouble() / 100)
        }
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(res).toString()
    }

    var isOpenAlert by mutableStateOf(false)
        private set

    fun openAlertDialog() = run { isOpenAlert = true }

    fun closeAlertDialog() = run { isOpenAlert = false }

    val listOfEmoji = listOf(
        "\uD83C\uDF4F", "\uD83C\uDF4E", "\uD83C\uDF50", "\uD83C\uDF4A", "\uD83C\uDF4B",
        "\uD83C\uDF4C", "\uD83C\uDF49", "\uD83C\uDF47", "\uD83C\uDF53", "\uD83C\uDF48",
        "\uD83C\uDF52", "\uD83C\uDF51", "\uD83E\uDD6D", "\uD83C\uDF4D", "\uD83E\uDD65",
        "\uD83E\uDD65", "\uD83E\uDD5D", "\uD83C\uDF45", "\uD83C\uDF46", "\uD83E\uDD51",
        "\uD83E\uDD66", "\uD83E\uDD6C", "\uD83E\uDD6C", "\uD83E\uDD52", "\uD83C\uDF36",
        "\uD83C\uDF3D", "\uD83E\uDD55", "\uD83E\uDDC4", "\uD83E\uDDC5", "\uD83E\uDD54",
        "\uD83C\uDF60", "\uD83E\uDD50", "\uD83E\uDD6F", "\uD83C\uDF5E", "\uD83E\uDD56",
        "\uD83E\uDD68", "\uD83E\uDDC0", "\uD83E\uDD5A", "\uD83C\uDF73", "\uD83E\uDDC8",
        "\uD83E\uDD5E", "\uD83E\uDDC7", "\uD83E\uDD53", "\uD83E\uDD69", "\uD83C\uDF57",
        "\uD83C\uDF56", "\uD83C\uDF2D", "\uD83C\uDF54", "\uD83C\uDF5F", "\uD83C\uDF55",
        "\uD83E\uDD6A", "\uD83E\uDD59", "\uD83E\uDDC6", "\uD83C\uDF2E", "\uD83C\uDF2F",
        "\uD83E\uDD57", "\uD83E\uDD58", "\uD83E\uDD6B", "\uD83C\uDF5D", "\uD83C\uDF5C",
        "\uD83C\uDF72", "\uD83C\uDF5B", "\uD83C\uDF63", "\uD83C\uDF71", "\uD83E\uDD5F",
        "\uD83E\uDDAA", "\uD83C\uDF64", "\uD83C\uDF59", "\uD83C\uDF5A", "\uD83C\uDF58",
        "\uD83C\uDF65", "\uD83E\uDD60", "\uD83E\uDD6E", "\uD83C\uDF62", "\uD83C\uDF61",
        "\uD83C\uDF67", "\uD83C\uDF68", "\uD83C\uDF66", "\uD83E\uDD67", "\uD83E\uDDC1",
        "\uD83C\uDF70", "\uD83C\uDF82", "\uD83C\uDF6E", "\uD83C\uDF6D", "\uD83C\uDF6C",
        "\uD83C\uDF6B", "\uD83C\uDF7F", "\uD83C\uDF69", "\uD83C\uDF6A", "\uD83C\uDF30",
        "\uD83E\uDD5C", "\uD83C\uDF6F", "\uD83E\uDD5B", "\uD83C\uDF7C", "☕️", "\uD83C\uDF75",
        "\uD83E\uDDC3", "\uD83E\uDD64", "\uD83C\uDF76", "\uD83C\uDF7A", "\uD83C\uDF77",
        "\uD83C\uDF7E", "\uD83E\uDD61", "\uD83E\uDD63"
    )
    val listOfProductName = listOf(
        "Hamburger",
        "Pizza",
        "Potato",
        "Morkov",
        "Kapysta",
        "Poshel nahyi"
    )


    var emojiToFood by mutableStateOf("")

    var bgColor by mutableStateOf(0)

    fun generateRandomEmoji() {
        val randomNum = (0..listOfEmoji.size).random()
        emojiToFood = listOfEmoji[randomNum]
    }
    fun generateRandomImage() {
        val bgImage = listOf(
            R.drawable.bg_add_food1,
            R.drawable.bg_add_food2,
            R.drawable.bg_add_food3
        )
        val randomNum = (bgImage.indices).random()
        bgColor =  bgImage[randomNum]

        Log.e("generateRandomImage", "bgColor: $bgColor", )
        Log.e("generateRandomImage", "randomNum: $randomNum", )
    }
    fun deleteDb() = viewModel.deleteTable()
    fun createFoodElement() {
        viewModelScope.launch {
            viewModel.insertFood(
                FoodEntity(
                    foodName = foodName,
                    calories = resCal.toInt(),
                    data = Date.getDayFood(Date.getCurrentDate()),
                    emogi = emojiToFood,
                    time = Date.getHourMin(Date.getCurrentDate())
                )
            )
        }
        foodName = ""
        calories = ""
        emojiToFood = ""
        weight = ""
    }


    fun validateCreateFoodElement(): Boolean = foodName.isNotEmpty() && weight.isNotEmpty() && calories.isNotEmpty()

}



