package com.example.mycityapp.data


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recommendation(
    @DrawableRes val image: Int,
    @StringRes val name: Int,
    @StringRes val desc: Int,
    val places: List<Place> = emptyList()
)
