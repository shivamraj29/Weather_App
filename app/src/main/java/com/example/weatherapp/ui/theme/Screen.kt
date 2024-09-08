package com.example.weatherapp.ui.theme

sealed class Screen(val route: String) {

    object MainScreen: Screen("mainscreen")
    object weatherDetail:Screen("weatherdetail")

}