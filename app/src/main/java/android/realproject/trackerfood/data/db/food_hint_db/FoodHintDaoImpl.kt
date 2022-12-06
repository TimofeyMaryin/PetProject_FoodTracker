package android.realproject.trackerfood.data.db.food_hint_db

import android.realproject.trackerfood.data.db.FoodEntity


interface FoodHintDaoImpl {

    fun getAllHint(): MutableList<FoodHintEntity>
    fun insertProductHint(foodHintEntity: FoodHintEntity)
    fun deleteProductHint(foodHintEntity: FoodHintEntity)

}