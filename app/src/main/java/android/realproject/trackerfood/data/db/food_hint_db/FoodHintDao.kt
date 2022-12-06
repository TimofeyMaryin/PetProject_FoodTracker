package android.realproject.trackerfood.data.db.food_hint_db

import android.realproject.trackerfood.data.db.FoodEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodHintDao {

    @Query("select * from foodhintentity")
    fun getAllHint(): MutableList<FoodHintEntity>

    @Insert
    fun insertProductHint(foodHintEntity: FoodHintEntity)

    @Delete
    fun deleteProductHint(foodHintEntity: FoodHintEntity)

}