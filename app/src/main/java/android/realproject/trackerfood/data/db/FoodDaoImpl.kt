package android.realproject.trackerfood.data.db



interface FoodDaoImpl {

    fun getFoodByData(data: String): MutableList<FoodEntity>
    fun deleteTable()
    suspend fun insertFood(food: FoodEntity)
    suspend fun deleteFood(food: FoodEntity)
}