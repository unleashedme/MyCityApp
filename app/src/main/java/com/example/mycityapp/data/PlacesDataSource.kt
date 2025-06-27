package com.example.mycityapp.data

import com.example.mycityapp.R
import com.example.mycityapp.data.Place.Malls
import com.example.mycityapp.data.Place.Parks
import com.example.mycityapp.data.Place.Restaurants
import com.example.mycityapp.data.Place.TouristPlaces

object PlacesDataSource {


    val restaurantList = listOf(
        Restaurants(
            name = R.string.krishna_haveli,
            image = R.drawable.krishnahaveli,
            location = R.string.krishna_haveli_location,
            description = R.string.krishna_haveli_description
        ),
        Restaurants(
            name = R.string.sharda_dining,
            image = R.drawable.sharda_dining,
            location = R.string.sharda_dining_location,
            description = R.string.sharda_dining_description
        ),
        Restaurants(
            name = R.string.royal_darbar,
            image = R.drawable.royal_darbar,
            location = R.string.royal_darbar_location,
            description = R.string.royal_darbar_description
        ),
        Restaurants(
            name = R.string.tunday_kababi,
            image = R.drawable.tunday_kababi,
            location = R.string.tunday_kababi_location,
            description = R.string.tunday_kababi_description
        ),
        Restaurants(
            name = R.string.terai,
            image = R.drawable.terai,
            location = R.string.terai_location,
            description = R.string.terai_description
        ),
        Restaurants(
            name = R.string.bindas_dhaba,
            image = R.drawable.bindasdhaba,
            location = R.string.bindas_dhaba_location,
            description = R.string.bindas_dhaba_description
        ),
        Restaurants(
            name = R.string.biryani_bay,
            image = R.drawable.birayanibay,
            location = R.string.biryani_bay_location,
            description = R.string.biryani_bay_description
        ),
        Restaurants(
            name = R.string.rangrezza,
            image = R.drawable.rangrezza,
            location = R.string.rangrezza_location,
            description = R.string.rangrezza_description
        )
    )

    val mallList = listOf(
        Malls(
            name = R.string.city_mall,
            image = R.drawable.citymall,
            location = R.string.city_mall_location,
            description = R.string.city_mall_description
        ),
        Malls(
            name = R.string.orion_mall,
            image = R.drawable.orionmall,
            location = R.string.orion_mall_location,
            description = R.string.orion_mall_description
        ),
        Malls(
            name = R.string.vishal_mega_mart,
            image = R.drawable.vishalmegamart,
            location = R.string.vishal_mega_mart_location,
            description = R.string.vishal_mega_mart_description
        ),
        Malls(
            name = R.string.v_mart,
            image = R.drawable.vmart,
            location = R.string.v_mart_location,
            description = R.string.v_mart_description
        ),
        Malls(
            name = R.string.trends,
            image = R.drawable.trends,
            location = R.string.trends_location,
            description = R.string.trends_description
        ),
        Malls(
            name = R.string.pantaloons,
            image = R.drawable.pantaloons,
            location = R.string.pantaloons_location,
            description = R.string.pantaloons_description
        )
    )

    val parkList = listOf(
        Parks(
            name = R.string.ambedkar_park,
            image = R.drawable.ambedkarpark,
            location = R.string.ambedkar_park_location,
            description = R.string.ambedkar_park_description
        ),
        Parks(
            name = R.string.vindyavasini_park,
            image = R.drawable.vindyavasinipark,
            location = R.string.vindyavasini_park_location,
            description = R.string.vindyavasini_park_description
        ),
        Parks(
            name = R.string.nehru_park,
            image = R.drawable.nehru_park,
            location = R.string.nehru_park_location,
            description = R.string.nehru_park_description
        )
    )

    val touristPlaceList = listOf(
        TouristPlaces(
            name = R.string.gorakhnath_temple,
            image = R.drawable.gorakhnath,
            location = R.string.gorakhnath_temple_location,
            description = R.string.gorakhnath_temple_description
        ),
        TouristPlaces(
            name = R.string.taramandal,
            image = R.drawable.taramandal,
            location = R.string.taramandal_location,
            description = R.string.taramandal_description
        ),
        TouristPlaces(
            name = R.string.ramgarh_taal,
            image = R.drawable.ramgarhtaal,
            location = R.string.ramgarh_taal_location,
            description = R.string.ramgarh_taal_description
        ),
        TouristPlaces(
            name = R.string.nauka_vihar,
            image = R.drawable.naukavihar,
            location = R.string.nauka_vihar_location,
            description = R.string.nauka_vihar_description
        ),
        TouristPlaces(
            name = R.string.budh_vihar_gate,
            image = R.drawable.buddhvihargate,
            location = R.string.budh_vihar_gate_location,
            description = R.string.budh_vihar_gate_description
        ),
        TouristPlaces(
            name = R.string.rail_museum,
            image = R.drawable.railmuseum,
            location = R.string.rail_museum_location,
            description = R.string.rail_museum_description
        )
    )

    val defaultPlace = restaurantList.get(0)
}