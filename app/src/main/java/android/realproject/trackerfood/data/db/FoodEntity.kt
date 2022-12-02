package android.realproject.trackerfood.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "food")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "food_name") val foodName: String,
    @ColumnInfo(name = "calories") val calories: Int,
    @ColumnInfo(name = "emogi") val emogi: String,
    @ColumnInfo(name = "data") val data: String,
)
