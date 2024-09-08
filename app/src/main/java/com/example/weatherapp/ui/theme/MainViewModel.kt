package com.example.weatherapp.ui.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    private var _resp = mutableStateOf(StateofPage())
    val resp:State<StateofPage> = _resp

    private var _query = mutableStateOf("")
    val query = _query


    val access_key =" 75d105f447b3453b8d2135952240509"
        var quer = "Jammu"

    fun fetchResp(query: String){
        viewModelScope.launch {
            try {


                val ApiResp = RetrofitHelper.getInstance().create(ApiService::class.java)
                val result = ApiResp.getCurrent(access_key, query)
                _resp.value = _resp.value.copy(
                    List = result,
                    loading = false,
                    error = null,
                    isEditing = false
                )
            }catch (e: Exception){
                _resp.value= _resp.value.copy(
                    loading = false,
                    error = "{${e.message}}"
                )
            }
        }
        }
    fun fetchForecast(query: String){
        viewModelScope.launch {
            try {
                val days = 10

                val ApiResp = RetrofitHelper.getInstance().create(ApiForcast::class.java)
                val result = ApiResp.getForecast(access_key, query, dates =  days )

                _resp.value = _resp.value.copy(
                    List = result,
                    loading = false,
                    error = null,
                    isEditing = false
                )
            }catch (e: Exception){
                _resp.value= _resp.value.copy(
                    loading = false,
                    error = "${e.message}"
                )
            }
        }

    }
    fun fetchSearch(query: String){
        viewModelScope.launch {
            try {
                val ApiResp = RetrofitHelper.getInstance().create(getSearch::class.java)
                val result = ApiResp.getSearch(access_key, query)

                _resp.value = _resp.value.copy(
                    search = result
                )
            }catch (e: Exception){
                _resp.value= _resp.value.copy(
                    loading = false,
                    error = "${e.message}"
                )
            }
        }

    }

data class StateofPage(
    val search:search? = null,
    val List: weatherData? = null,
    val loading: Boolean = false,
    val error: String? = null,
    var isEditing:Boolean = true,


)



}








