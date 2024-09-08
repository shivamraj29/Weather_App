package com.example.weatherapp.ui.theme

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    val forecastday: List<ForecastDay>
):Parcelable

@Parcelize
data class ForecastDay(
    val date: String,
   // val date_epoch: Long,
    val day: Day,
    val astro: Astro,
   // val hour: List<Hour>
):Parcelable

@Parcelize
data class Day(
    val maxtemp_c: Double,
    //val maxtemp_f: Double,
    val mintemp_c: Double,
   // val mintemp_f: Double,
    val avgtemp_c: Double,
    //val avgtemp_f: Double,
    //val maxwind_mph: Double,
    //val maxwind_kph: Double,
    val totalprecip_mm: Double,
    //val totalprecip_in: Double,
    //val totalsnow_cm: Double,
    val avgvis_km: Double,
    //val avgvis_miles: Double,
    val avghumidity: Int,
    //val daily_will_it_rain: Int,
    val daily_chance_of_rain: Int,
    val daily_will_it_snow: Int,
    //val daily_chance_of_snow: Int,
    val condition: Condition,
    val uv: Double
):Parcelable


@Parcelize
data class Astro(
    val sunrise: String,
    val sunset: String,
   // val moonrise: String,
    //val moonset: String,
    val moon_phase: String,
    val moon_illumination: Int,
    //val is_moon_up: Int,
    //val is_sun_up: Int
):Parcelable
