package com.example.weatherapp.ui.theme

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitHelper{
    val base_url = "http://api.weatherapi.com/v1/"

    fun getInstance():Retrofit{
        return Retrofit.Builder().baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}

interface ApiService{
    @GET("current.json")
    suspend fun getCurrent(
        @Query("key")access_key:String,
        @Query("q")query:String
    ):weatherData

}
