package com.example.mycityapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class Place(
    @StringRes open val name: Int,
    @DrawableRes open val image: Int,
    @StringRes open val location: Int,
    @StringRes open val description: Int
){
    data class Restaurants(
        override val name: Int,
        override val image: Int,
        override val location: Int,
        override val description: Int
    ) : Place(name,image,location,description)

    data class Malls(
        override val name: Int,
        override val image: Int,
        override val location: Int,
        override val description: Int
    ) : Place(name,image,location,description)

    data class Parks(
        override val name: Int,
        override val image: Int,
        override val location: Int,
        override val description: Int
    ) : Place(name,image,location,description)

    data class TouristPlaces(
        override val name: Int,
        override val image: Int,
        override val location: Int,
        override val description: Int
    ) : Place(name,image,location,description)
}
