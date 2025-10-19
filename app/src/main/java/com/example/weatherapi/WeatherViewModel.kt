package com.example.weatherapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapi.api.ApiKey
import com.example.weatherapi.api.RetrofitInstance
import com.example.weatherapi.api.response.WeatherResponse
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherApi= RetrofitInstance.weatherApi
    private val _weatherResult= MutableLiveData<NetworkResponse<WeatherResponse>>()
     val weatherResult: LiveData<NetworkResponse<WeatherResponse>> = _weatherResult


    fun getData(city: String){

        _weatherResult.value= NetworkResponse.Loading
        viewModelScope.launch {

            val response= weatherApi.getWeather(apiKey = ApiKey.apiKey, location = city)
            if(response.isSuccessful){

                response.body()?.let {
                    _weatherResult.value= NetworkResponse.Success(it)
                }
            }else{

                _weatherResult.value= NetworkResponse.Error("Failed to load data")

            }
        }
    }
}