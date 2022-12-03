package android.realproject.trackerfood.model

data class SortCalByDayCount(
    val title: String,
    var selected: Boolean = false,
    val onAction: () -> Unit,
)
