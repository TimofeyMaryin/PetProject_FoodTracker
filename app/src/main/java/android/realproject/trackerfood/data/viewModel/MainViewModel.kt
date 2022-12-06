package android.realproject.trackerfood.data.viewModel

import android.app.Application
import android.realproject.trackerfood.data.db.ApplicationDataBase
import android.realproject.trackerfood.data.db.FoodDaoImpl
import android.realproject.trackerfood.data.db.FoodEntity
import android.realproject.trackerfood.data.db.avatar_db.AvatarDaoImpl
import android.realproject.trackerfood.data.db.avatar_db.AvatarEntity
import android.realproject.trackerfood.data.db.food_hint_db.FoodHintDaoImpl
import android.realproject.trackerfood.data.db.food_hint_db.FoodHintEntity
import android.realproject.trackerfood.model.HintCaloricModel
import android.realproject.trackerfood.model.SortCalByDayCount
import android.realproject.trackerfood.model.date.Date
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainViewModel(
    application: Application
): ViewModel(), FoodDaoImpl, AvatarDaoImpl, FoodHintDaoImpl {

    private val foodDao = ApplicationDataBase.getInstance(application = application).foodDao()
    private val avatarDao = ApplicationDataBase.getInstance(application).avatarDao()
    private val foodHintDao = ApplicationDataBase.getInstance(application = application).foodHintDao()

    override suspend fun insertFood(food: FoodEntity) = foodDao.insertFood(food)
    override fun getFoodByData(data: String): MutableList<FoodEntity> = foodDao.getFoodByData(data)
    override fun deleteTable() = foodDao.deleteTable()
    override suspend fun deleteFood(food: FoodEntity) = foodDao.deleteFood(food)
    fun getAllAvatar(): MutableList<AvatarEntity> = avatarDao.getAllAvatar()

    override fun getAllHint(): MutableList<FoodHintEntity> = foodHintDao.getAllHint()
    override fun insertProductHint(foodHintEntity: FoodHintEntity) = foodHintDao.insertProductHint(foodHintEntity)
    override fun deleteProductHint(foodHintEntity: FoodHintEntity) = foodHintDao.deleteProductHint(foodHintEntity)


    fun calculateCaloric(data: String): Int = calcCaloricByDay(data = data)
//    fun hintCaloricElement(index: Int): HintCaloricModel {
//        return when(index) {
//            0 -> { HintCaloricModel("За день") {} }
//            1 -> { HintCaloricModel("За неделю") {} }
//            2 -> { HintCaloricModel("За месяц") {} }
//            3 -> { HintCaloricModel("За год") {} }
//            else -> { HintCaloricModel("Пошел нахуй") {} }
//        }
//    }

    var showOverview by mutableStateOf(false)
    fun overViewCaloricElement(index: Int): HintCaloricModel{
        return when(index){
            0 -> HintCaloricModel("Overview by day") {}
            1 -> HintCaloricModel("Overview by week") {}
            2 -> HintCaloricModel("Overview by month") {}
            3 -> HintCaloricModel("Overview by year"){}
            else -> HintCaloricModel("Нахуй иди") {}
        }
    }

    private fun calcCaloricByDay(data: String): Int {
        var res by mutableStateOf(0)
        for (i in 0 until getFoodByData(data = data).size) {
            res += getFoodByData(data = data)[i].calories
        }

        return res
    }


    fun checkAvailabilityAvatar() = getAllAvatar().size == 0


    override fun deleteAvatar(avatarEntity: AvatarEntity) = avatarDao.deleteAvatar(avatarEntity)
    override fun insertAvatar(avatarModel: AvatarEntity) = avatarDao.insertAvatar(avatarModel)
    override fun deleteAllAvatar() = avatarDao.deleteAllAvatar()


    val listOfMotivationPhrases = mutableListOf(
        "Отличный день чтобы похудеть © Разраб",
        "Возьми свои ляшки под упряжку ©Разраб",
        "Путешествие к тысяче фунтов начинается с одного гамбургера",
        "Нет ничего лучше, чем прожить жизнь в стремлении стать совершеннее",
        "Если вы не даете своему телу самое лучшее, то попросту обкрадываете себя",
        "Начинайте представлять себя таким человеком, каким хотели бы видеть себя",
        "В конце концов ваше здоровье — ваша ответственность",
        "Возможность может постучать только один раз, но искушение опирается на дверной звонок",
        "Голод – это твой друг, он не предаст тебя как еда.",
        "Фитнесы, диеты это для лошар, а моя фигура идеальный шар...",
        "Худеешь - грудь сдувается. Грудь растет - толстеет задница... Тяжело быть бабой.",
        "Пустой кошелёк чистит организм лучше любой из диет.",
        "Села на диету... Отказалась от пива... Перестала есть пoсле шeсти... И за три недели потеряла... ВНИМАНИЕ !!! ... 21 день весёлoй и счастливой жизни.)))",
        "Мысли о том, что надо бы сесть на диету, приходят обычно после сытного обеда и исчезают за полчаса до ужина..."
    )

    var indexTouchProductIndex by mutableStateOf(0)
    fun changeIndexTouchProductIndex(index: Int) = run { indexTouchProductIndex = index }


}

