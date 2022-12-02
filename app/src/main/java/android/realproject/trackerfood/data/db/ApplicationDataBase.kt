package android.realproject.trackerfood.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        FoodEntity::class
    ],
    version = 1
)
abstract class ApplicationDataBase: RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile var INSTANCE: ApplicationDataBase? = null

        fun getInstance(application: Application): ApplicationDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        application,
                        ApplicationDataBase::class.java,
                        "food.db"
                    ).allowMainThreadQueries().build()
                }

                INSTANCE = instance
                return INSTANCE!!

            }
        }
    }
}