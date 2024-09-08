package com.example.weatherapp.ui.theme
class search : ArrayList<searchItem>()

data class searchItem(
    val country: String,
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val url: String
)