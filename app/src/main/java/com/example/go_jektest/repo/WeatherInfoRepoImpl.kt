package com.example.go_jektest.repo

import com.example.go_jektest.BuildConfig
import com.example.go_jektest.feature.WeatherInfoDisplayData
import com.example.go_jektest.network.WeatherApi
import io.reactivex.Observable
import javax.inject.Inject

class WeatherInfoRepoImpl @Inject constructor(val api: WeatherApi) :WeatherInfoRepo {

    override fun fetchWeatherInfo(param : WeatherInfoRepoImpl.WeatherInfoParam): Observable<WeatherInfoDisplayData> {
        return api.getTemperature(BuildConfig.APIXU_KEY,param.placeName,param.numberOfDays)
            .map{
                val data = WeatherInfoDisplayData()
                data.updateData(it)
                data
            }
    }

    data class WeatherInfoParam(val placeName:String , val numberOfDays:Int = 1)
}