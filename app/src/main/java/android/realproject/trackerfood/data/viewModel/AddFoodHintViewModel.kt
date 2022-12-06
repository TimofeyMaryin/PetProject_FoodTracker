package android.realproject.trackerfood.data.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AddFoodHintViewModel: ViewModel() {
    var foodTitle by mutableStateOf("")
    var foodCaloric by mutableStateOf("")
    var foodIcon by mutableStateOf("")

    fun changeValue(value: String, index: Int) {
        when(index){
            0 -> { foodTitle = value }
            1 -> { foodCaloric = value }
            2 -> { if(foodIcon.length <= 1) foodIcon = value }
        }
    }

    private fun validateCurrentEnterEmojiData(): Boolean {
        return true
    }

}

// 😂
// " 😔 "

// 🥗