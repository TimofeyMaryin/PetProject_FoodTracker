package android.realproject.trackerfood.data.db.avatar_db

import android.realproject.trackerfood.model.AvatarModel
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface AvatarDao {
    @Insert
    fun insertAvatar(avatarModel: AvatarEntity)

    @Query("select * from avatarentity")
    fun getAllAvatar(): MutableList<AvatarEntity>

    @Delete
    fun deleteAvatar(avatarEntity: AvatarEntity)

    @Query("delete from AvatarEntity")
    fun deleteAllAvatar()
}