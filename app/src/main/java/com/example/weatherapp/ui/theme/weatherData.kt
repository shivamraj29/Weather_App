package com.example.weatherapp.ui.theme

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class weatherData(
    val current: Current,
    val location: Location,
    val forecast: Forecast
):Parcelable

@Parcelize
data class Location(
    val country: String,
    //val lat: Double,
    val localtime: String,
   // val localtime_epoch: Int,
    //val lon: Double,
    val name: String,
    val region: String,
   // val tz_id: String
):Parcelable

@Parcelize
data class Current(
    val cloud: Int,
    val condition: Condition,
    val dewpoint_c: Double,
   // val dewpoint_f: Double,
    val feelslike_c: Double,
   // val feelslike_f: Double,
   // val gust_kph: Double,
   // val gust_mph: Double,
   // val heatindex_c: Double,
    //val heatindex_f: Int,
    val humidity: Int,
   // val is_day: Int,
    val last_updated: String,
    //val last_updated_epoch: Int,
    //val precip_in: Int,
    val precip_mm: Double,
    val pressure_in: Double,
    //val pressure_mb: Int,
    val temp_c: Double,
    //val temp_f: Double,
    val uv: Int,
    val vis_km: Double,
    //val vis_miles: Int,
   // val wind_degree: Int,
    val wind_dir: String,
    val wind_kph: Double,
    //val wind_mph: Double,
    val windchill_c: Double,
    //val windchill_f: Double
):Parcelable

@Parcelize
data class Condition(
    val code: Int,
    val icon: String,
    val text: String
):Parcelable

