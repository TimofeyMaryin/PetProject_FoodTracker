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
import android.realproject.trackerfood.model.SortCalByDayCount
import android.realproject.trackerfood.model.date.Date
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

    override suspend fun insertFood(food: FoodEntity) = foodDao.insertFood(food)
    override fun getFoodByData(data: String): MutableList<FoodEntity> = foodDao.getFoodByData(data)
    override fun deleteTable() = foodDao.deleteTable()
    override suspend fun deleteFood(food: FoodEntity) = foodDao.deleteFood(food)
    fun getAllAvatar(): MutableList<AvatarEntity> = avatarDao.getAllAvatar()

    fun calculateCaloric(data: String, typeIndex: Int): Int {
        val res by mutableStateOf(0)
        return when (typeIndex) {
            0 -> { cycleCalcData(data = data, res) }
            1 -> { 2 }
            2 -> { 3}
            3 -> { 4 }
            else  -> { -1 }
        }

    }
    private fun cycleCalcData(data: String, res: Int): Int {
        var _res by mutableStateOf(res)
        for (i in 0 until getFoodByData(data = data).size) {
            _res += getFoodByData(data = data)[i].calories
        }

        return _res
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


    val sortCalByDayCount = mutableListOf(
        mutableStateOf( SortCalByDayCount("День",true) { sortByDayTub(0) }),
        mutableStateOf( SortCalByDayCount("Неделя",false) { sortByDayTub(1) } ),
        mutableStateOf( SortCalByDayCount("Месяц",false) { sortByDayTub(2) }),
        mutableStateOf( SortCalByDayCount("Год",false) { sortByDayTub(3) }),
    )

    private fun sortByDayTub(index: Int): Int {
        return when(index) {
            0 -> { calculateCaloric(Date.getDayFood(Date.getCurrentDate()), index) }
            1 -> { clickBySortDay(7, 1) }
            2 -> { clickBySortDay(31, 2) }
            3 -> { clickBySortDay(361, 3) }
            else -> { -1 }
        }
    }
    private fun clickBySortDay(date: Int, index: Int): Int {
        clearSortSelect()
        sortCalByDayCount[index].value.selected = true
        return date
    }
    private fun clearSortSelect() {
        for (i in 0 until sortCalByDayCount.size) {
            sortCalByDayCount[i].value.selected = false
        }
    }
}