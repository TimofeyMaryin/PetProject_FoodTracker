package android.realproject.trackerfood.model

import android.realproject.trackerfood.R

data class InstanceProduct(
    val name: String,
    val caloric: Int,
    val icon: Int = R.drawable.ic_product_icon,
)
