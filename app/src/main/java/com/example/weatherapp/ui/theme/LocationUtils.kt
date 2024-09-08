package com.example.weatherapp.ui.theme

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class LocationUtils(val context: Context) {

    private val _fusedLocationClient: FusedLocationProviderClient
    = LocationServices.getFusedLocationProviderClient(context)

    @Suppress("MissingPermission")
    fun requestLocationUpdates(viewModel: locationViewModel){
        val locationCallBack= object:LocationCallback(){
            override fun onLocationResult(locationResult:LocationResult){
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let {
                    val location = locationData(
                        latitude = it.latitude,
                        longitude = it.longitude
                    )
                    viewModel.newLocation(location)
                }
            }
        }
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, 1000
        ).build()

        _fusedLocationClient.requestLocationUpdates(locationRequest, locationCallBack, Looper.getMainLooper())

    }


    fun hasLocationPermission(context: Context):Boolean{
        return ContextCompat.checkSelfPermission(context
                , Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED
                &&
        return ContextCompat.checkSelfPermission(context
                    , Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED
    }
}