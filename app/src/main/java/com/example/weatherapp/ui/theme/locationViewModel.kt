package com.example.weatherapp.ui.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class locationViewModel:ViewModel() {

    private var _location = mutableStateOf<locationData?>(null)
    val location:State<locationData?> = _location

    fun newLocation(newLocation: locationData){
        _location.value = newLocation
    }
}