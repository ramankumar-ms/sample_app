package com.example.hyd

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.hyd.ui.data.HydUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
//import com.example.hyd.ui.data
import androidx.lifecycle.viewmodel.compose.viewModel



class HydViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HydUiState())
    val uiState: StateFlow<HydUiState> = _uiState.asStateFlow()

    fun setSelectedCoffee(coffee: String) {
        _uiState.update { currentState ->
            currentState.copy(selectedCoffee = coffee)
        }
    }

    fun setSelectedRestaurant(restaurant: String) {
        _uiState.update { currentState ->
            currentState.copy(selectedRestaurant = restaurant)
        }
    }

    fun resetOrder() {
        _uiState.value = HydUiState()
    }
}



