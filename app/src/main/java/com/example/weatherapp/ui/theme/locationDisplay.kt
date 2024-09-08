package com.example.weatherapp.ui.theme

import android.Manifest
import android.content.Context
import android.icu.text.LocaleDisplayNames
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.MainActivity

@Composable
fun locationDisplay(
    context: Context,
    locationUtils: LocationUtils,
    viewModel: locationViewModel,
    viewMainModel: MainViewModel
){

    val location= viewModel.location.value
        val requestPermissionLauncher= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {permissions->
            if (permissions[Manifest.permission.ACCESS_COARSE_LOCATION]== true
                &&permissions[Manifest.permission.ACCESS_FINE_LOCATION]== true){

            }else{
               val rationaleRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                   context as MainActivity,
                   Manifest.permission.ACCESS_COARSE_LOCATION
               )|| ActivityCompat.shouldShowRequestPermissionRationale(
                   context, Manifest.permission.ACCESS_FINE_LOCATION
               )
                if (rationaleRequired){
                    Toast.makeText(context, "Location permission is required", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context, "please enable", Toast.LENGTH_LONG).show()

                }
            }
        })
 Column(horizontalAlignment = Alignment.CenterHorizontally,
     modifier = Modifier
         .fillMaxWidth()
         .padding(16.dp)) {

     Button(
         onClick = {
             if (locationUtils.hasLocationPermission(context)) {

                 locationUtils.requestLocationUpdates(viewModel)
             } else {
                 requestPermissionLauncher.launch(
                     arrayOf(
                         Manifest.permission.ACCESS_COARSE_LOCATION,
                         Manifest.permission.ACCESS_FINE_LOCATION
                     )
                 )
             }
                viewMainModel.query.value= ""
                   },
         colors = ButtonColors(containerColor = Color(0,98,105),
             contentColor = MaterialTheme.colorScheme.surfaceVariant, disabledContentColor = Color.LightGray, disabledContainerColor = Color.Cyan ),
         elevation = ButtonDefaults.buttonElevation(16.dp,8.dp,8.dp,12.dp)
     ){
         Text(text = "Search for Current Location", fontSize = 16.sp, fontFamily = FontFamily.SansSerif)
     }
 }}



@Composable
fun myapp(viewMainModel: MainViewModel,
            locationData: locationData){
    val context = LocalContext.current
    val locationUtils = LocationUtils(context)
    val viewModel:locationViewModel = viewModel()
    val location = viewModel.location.value
    val askLocation by remember {
        mutableStateOf(false)
    }

    locationDisplay(context = context, locationUtils = locationUtils, viewModel = viewModel, viewMainModel =  viewMainModel )
    if (viewModel.location.value != null && viewMainModel.query.value == ""){
       val loc = "${location?.latitude},${location?.longitude}"
        viewMainModel.fetchForecast(loc)

    }


}