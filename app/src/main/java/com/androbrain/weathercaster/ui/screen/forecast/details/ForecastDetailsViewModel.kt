package com.androbrain.weathercaster.ui.screen.forecast.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ForecastDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val args = ForecastDetailsArgs(savedStateHandle)
    private val _state = MutableStateFlow(ForecastDetailsState(model = args.model))
    val state = _state.asStateFlow()
}
