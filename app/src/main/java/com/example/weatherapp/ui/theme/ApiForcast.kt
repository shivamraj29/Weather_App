package com.example.weatherapp.ui.theme

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiForcast {

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key")access_key:String,
        @Query("q")query: String,
        @Query("days")dates:Int
    ):weatherData

}
