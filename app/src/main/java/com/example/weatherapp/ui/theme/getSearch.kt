package com.example.weatherapp.ui.theme

import retrofit2.http.GET
import retrofit2.http.Query

interface getSearch {

    @GET("search.json")
    suspend fun getSearch(
        @Query("key")access_key:String,
        @Query("q")query: String,
    ):search
}