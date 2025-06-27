package com.example.mycityapp.model

import com.example.mycityapp.data.PlacesDataSource
import com.example.mycityapp.data.Place
import com.example.mycityapp.data.Recommendation
import com.example.mycityapp.data.RecommendationsDataSource

data class MyCityUiState(
    val currentPlace: Place = PlacesDataSource.defaultPlace,
    val currentRecommendation: Recommendation = RecommendationsDataSource.defaultList,
    val isShowingPlacesPage: Boolean = true,
    val isShowingRecommendationsPage: Boolean = true
)

