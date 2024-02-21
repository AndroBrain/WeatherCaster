package com.androbrain.weathercaster.ui.screen.forecast

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ForecastViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val args = ForecastArgs(savedStateHandle)

    private val _state = MutableStateFlow(ForecastState(args.model))
    val state = _state.asStateFlow()
}
