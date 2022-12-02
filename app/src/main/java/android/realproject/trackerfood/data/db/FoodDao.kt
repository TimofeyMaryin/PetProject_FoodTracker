package android.realproject.trackerfood.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao {
    @Insert
    suspend fun insertFood(food: FoodEntity)

    @Query("select * from food")
    fun getAllFoods(): MutableList<FoodEntity>

    @Query("select * from food where data = :data")
    fun getFoodByData(data: String): MutableList<FoodEntity>

    @Delete
    suspend fun deleteFood(food: FoodEntity)

    @Query("delete from food")
    fun deleteTable()
}