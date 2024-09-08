package com.example.weatherapp.ui.theme

import android.accessibilityservice.AccessibilityService.ScreenshotResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import java.time.LocalDate

@Composable
fun WeatherApp(navController: NavHostController){
    val weatherViewModel: MainViewModel = viewModel()
    val viewState by weatherViewModel.resp
    
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route){
            mainScreen(viewState = viewState, navigateToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.weatherDetail.route)

            }, locationData = locationData(0.00,0.00))
        }
        composable(route = Screen.weatherDetail.route){
            val Data = navController.previousBackStackEntry?.savedStateHandle?.
                    get<weatherData>("cat")?:weatherData(current =Current(0,Condition(0,"",""),0.00,0.00,0,"",
                        0.00,0.00,0.00,0,0.00,"",0.00,0.00), location = Location("","","",""),
                        forecast = Forecast(forecastday = listOf(ForecastDay("0",Day(0.00,0.00,0.00,0.00,0.00,0,0,0,Condition(0,"",""),
                            0.00),Astro("","","",0))))
            )
            weatherDetail(data = Data)
           // navController.popBackStack()
        }
    }}
