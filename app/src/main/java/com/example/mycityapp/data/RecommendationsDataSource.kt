package com.example.mycityapp.data

import com.example.mycityapp.R

object RecommendationsDataSource {


    val recommendationsList = listOf(

        Recommendation(
            image = R.drawable.restaurantslogo,
            name = R.string.restaurants,
            desc = R.string.restaurants_short_desc,
            places = PlacesDataSource.restaurantList
        ),
        Recommendation(
            image = R.drawable.park,
            name = R.string.parks,
            desc = R.string.parks_short_desc,
            places = PlacesDataSource.parkList
        ),
        Recommendation(
            image = R.drawable.mallslogo,
            name = R.string.malls,
            desc = R.string.malls_short_desc,
            places = PlacesDataSource.mallList
        ),
        Recommendation(
            image = R.drawable.travellogo,
            name = R.string.tourist_laces,
            desc = R.string.tourist_places_short_desc,
            places = PlacesDataSource.touristPlaceList
        )
    )

    val defaultList = recommendationsList.get(0)
}