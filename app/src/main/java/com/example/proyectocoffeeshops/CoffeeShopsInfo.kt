package com.example.proyectocoffeeshops

import androidx.annotation.DrawableRes
import androidx.compose.runtime.MutableState

data class CoffeeShopsInfo(
    var coffeShopName: String,
    var adressCoffeeShop: String,
    @DrawableRes var photo: Int,
    val rating: Double

)