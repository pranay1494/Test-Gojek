package com.example.go_jektest.feature

import com.example.go_jektest.network.model.Temprature
import com.example.go_jektest.network.model.TempratureHelper
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class WeatherInfoDisplayData {

    var currentLocation : String = ""
    var currentTemp : Double = 0.0
    val listOfForecasts = ArrayList<TempratureHelper>()

    fun updateData(data: Temprature?){
        currentTemp = data?.current?.temprature?:0.0
        currentLocation = data?.location?.name?:""
        data?.forecast?.day?.mapTo(listOfForecasts) { item->
            TempratureHelper(dateToDayConvertor(item.dateEpoch?:0),item.day?.avgTemp?.toString()?:"")
        }
    }

    fun dateToDayConvertor(date: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = TimeUnit.SECONDS.toMillis(date)
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
    }

}