package com.example.go_jektest.network

import com.example.go_jektest.network.model.Temprature
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast.json")
    fun getTemperature(@Query("key") key: String, @Query("q") placeName: String, @Query("days") foreCastDays: Int)
            : Observable<Temprature>
}