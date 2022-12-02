package android.realproject.trackerfood.data.viewModel

import android.app.Application
import android.realproject.trackerfood.R
import android.realproject.trackerfood.data.db.ApplicationDataBase
import android.realproject.trackerfood.data.db.FoodDao
import android.realproject.trackerfood.data.db.FoodDaoImpl
import android.realproject.trackerfood.data.db.FoodEntity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel(
    application: Application
): ViewModel(), FoodDaoImpl {

    private val foodDao = ApplicationDataBase.getInstance(application = application).foodDao()
    val getAllFood = foodDao.getAllFoods()

    override suspend fun insertFood(food: FoodEntity) = foodDao.insertFood(food)
    override fun getFoodByData(data: String): MutableList<FoodEntity> = foodDao.getFoodByData(data)
    override fun deleteTable() = foodDao.deleteTable()
    override suspend fun deleteFood(food: FoodEntity) = foodDao.deleteFood(food)


    fun calculateCaloric(): Int {
        var res by mutableStateOf(0)

        for (i in 0 until getAllFood.size) {
            res += getAllFood[i].calories
        }

        return res
    }
}