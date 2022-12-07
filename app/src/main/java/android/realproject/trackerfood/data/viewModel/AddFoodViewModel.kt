package android.realproject.trackerfood.data.viewModel

import android.realproject.trackerfood.R
import android.realproject.trackerfood.data.db.FoodEntity
import android.realproject.trackerfood.model.InstanceProduct
import android.realproject.trackerfood.model.date.Date
import android.realproject.trackerfood.utils.LIST_OF_EMOJI
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
            2 ->  { if (validateEnterValue(value)) weight = value }
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
    var emojiToFood by mutableStateOf("")
    var bgColor by mutableStateOf(R.drawable.bg_add_food3)

    fun openAlertDialog() = run { isOpenAlert = true }
    fun closeAlertDialog() = run { isOpenAlert = false }


    fun generateRandomEmoji() {
        val randomNum = (0.. LIST_OF_EMOJI.size).random()
        emojiToFood = LIST_OF_EMOJI[randomNum]
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



