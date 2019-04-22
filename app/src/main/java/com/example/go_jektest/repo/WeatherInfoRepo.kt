package com.example.go_jektest.repo

import com.example.go_jektest.feature.WeatherInfoDisplayData
import io.reactivex.Observable

interface WeatherInfoRepo {
    fun fetchWeatherInfo(param : WeatherInfoRepoImpl.WeatherInfoParam) : Observable<WeatherInfoDisplayData>
}