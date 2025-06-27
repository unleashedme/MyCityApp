package com.example.mycityapp.ui.theme

import androidx.lifecycle.ViewModel
import com.example.mycityapp.data.Place
import com.example.mycityapp.data.Recommendation
import com.example.mycityapp.model.MyCityUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState.asStateFlow()

    fun updateCurrentRecommendation(recommendation: Recommendation) {
        _uiState.update {
            it.copy(currentRecommendation = recommendation)
        }
    }

    fun updateCurrentPlace(selectedPlace: Place){
        _uiState.update {
            it.copy(currentPlace = selectedPlace)
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(
                isShowingPlacesPage = true
            )
        }
    }


    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingPlacesPage = false)
        }
    }

    fun navigateToPlacesPage(){
        _uiState.update {
            it.copy(isShowingRecommendationsPage = false)
        }
    }
    fun navigateToRecommendationsPage(){
        _uiState.update {
            it.copy(isShowingRecommendationsPage = true)
        }
    }
}