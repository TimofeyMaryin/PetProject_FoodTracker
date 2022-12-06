package android.realproject.trackerfood.data.db.food_hint_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class FoodHintEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") val productTitle: String,
    @ColumnInfo(name = "caloric") val countCaloric: Int,
    @ColumnInfo(name = "emogi") val emogi: String
)
