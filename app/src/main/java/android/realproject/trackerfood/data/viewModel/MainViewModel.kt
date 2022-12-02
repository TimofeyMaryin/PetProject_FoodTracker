package android.realproject.trackerfood.data.viewModel

import android.app.Application
import android.realproject.trackerfood.R
import android.realproject.trackerfood.data.db.ApplicationDataBase
import android.realproject.trackerfood.data.db.FoodDao
import android.realproject.trackerfood.data.db.FoodDaoImpl
import android.realproject.trackerfood.data.db.FoodEntity
import android.realproject.trackerfood.data.db.avatar_db.AvatarDaoImpl
import android.realproject.trackerfood.data.db.avatar_db.AvatarEntity
import android.realproject.trackerfood.model.AvatarModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel(
    application: Application
): ViewModel(), FoodDaoImpl, AvatarDaoImpl {

    private val foodDao = ApplicationDataBase.getInstance(application = application).foodDao()
    private val avatarDao = ApplicationDataBase.getInstance(application).avatarDao()

    val getAllAvatar = avatarDao.getAllAvatar()
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

    fun checkAvailabilityAvatar() = getAllAvatar.size == 0


    override fun deleteAvatar(avatarEntity: AvatarEntity) = avatarDao.deleteAvatar(avatarEntity)
    override fun insertAvatar(avatarModel: AvatarEntity) = avatarDao.insertAvatar(avatarModel)
    override fun deleteAllAvatar() = avatarDao.deleteAllAvatar()


    val listOfMotivationPhrases = mutableListOf(
        "Отличный день чтобы похудеть © Разраб",
        "Возьми свои ляшки под упряжку ©Разраб",
        "Путешествие к тысяче фунтов начинается с одного гамбургера",
        "Нет ничего лучше, чем прожить жизнь в стремлении стать совершеннее",
        "Если вы не даете своему телу самое лучшее, то попросту обкрадываете себя",
        "Начинайте представлять себя таким человеком, каким хотели бы видеть себя"
    )

}