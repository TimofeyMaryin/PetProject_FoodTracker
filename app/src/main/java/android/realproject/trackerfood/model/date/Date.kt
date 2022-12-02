package android.realproject.trackerfood.model.date

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

object Date {

    fun getCurrentTime(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        return LocalDate.now().format(formatter)
    }

    fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()

        return LocalDateTime.of(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            calendar.get(Calendar.SECOND)
        ).toString()
    }

    fun getHourMin(date: String): String {
        return date.substringAfterLast("T")
    }

    fun getDateToday(): String {
        return "${LocalDateTime.now().dayOfMonth} ${LocalDateTime.now().month}"
    }

    fun getDayFood(date: String): String {
        return date
            .substringAfter("-")
            .substringBefore("T")
    }


}