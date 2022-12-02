package android.realproject.trackerfood.data.db.avatar_db


interface AvatarDaoImpl {

    fun deleteAvatar(avatarEntity: AvatarEntity)
    fun insertAvatar(avatarModel: AvatarEntity)
    fun deleteAllAvatar()

}