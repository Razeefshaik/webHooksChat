package com.example.weatherapi.api

import com.example.weatherapi.api.response.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val basUrrl="https://api.weatherapi.com";
    private fun getInstance(): Retrofit{
        return Retrofit.Builder().baseUrl(basUrrl).addConverterFactory(GsonConverterFactory.create()).build();
    }
    val weatherApi: ApiService= getInstance().create(ApiService::class.java )
}