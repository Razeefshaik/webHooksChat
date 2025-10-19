package com.example.weatherapi.api

import com.example.weatherapi.api.response.WeatherResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/current.json")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String
    ): Response<WeatherResponse>


}