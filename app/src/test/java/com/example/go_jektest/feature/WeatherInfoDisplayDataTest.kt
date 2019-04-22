package com.example.go_jektest.feature

import com.example.go_jektest.network.model.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class WeatherInfoDisplayDataTest {


    private lateinit var displayData: WeatherInfoDisplayData

    @Before
    fun init() {
        displayData = WeatherInfoDisplayData()
    }

    @Test
    fun test_updateData_null() {
        displayData.updateData(null)
        assertTrue(displayData.currentTemp.equals(0.0))
        assertTrue(displayData.currentLocation.equals(""))
        assertTrue(displayData.listOfForecasts.isEmpty())
    }

    @Test
    fun test_updateData_tempNotNull_LocationNull_ForecastNull_CurrentNotNull() {
        val temprature = Temprature(current = Current(1.2))
        displayData.updateData(temprature)
        assertTrue(displayData.currentTemp == 1.2)
        assertTrue(displayData.currentLocation.equals(""))
        assertTrue(displayData.listOfForecasts.isEmpty())
    }

    @Test
    fun test_updateData_tempNotNull_CurrentNull_ForecastNull_LocationNull() {
        val temprature = Temprature(Location(name = "Bengaluru"))
        displayData.updateData(temprature)
        assertTrue(displayData.currentLocation.equals("Bengaluru"))
        assertTrue(displayData.currentTemp == 0.0)
        assertTrue(displayData.listOfForecasts.isEmpty())
    }

    @Test
    fun test_updateData_tempNotNull_CurrentNull_ForecastNotNull_ListOfForeCastNull() {
        val temprature = Temprature(forecast = Forecast(day = null))
        displayData.updateData(temprature)
        assertTrue(displayData.currentTemp.equals(0.0))
        assertTrue(displayData.currentLocation.equals(""))
        assertTrue(displayData.listOfForecasts.isEmpty())
    }

    @Test
    fun test_updateData_tempNotNull_CurrentNull_ForecastNotNull() {
        val listOfDays = ArrayList<Forecastday>()

        val day1 = Day(avgTemp = 1.0)
        val day2 = Day(avgTemp = 2.0)

        val forecastDay1 = Forecastday(date = "", day = day1, dateEpoch = 123)
        val forecastDay2 = Forecastday(date = "", day = day2, dateEpoch = 123)

        listOfDays.add(forecastDay1)
        listOfDays.add(forecastDay2)

        val temprature = Temprature(forecast = Forecast(listOfDays))
        displayData.updateData(temprature)
        assertTrue(displayData.listOfForecasts.size > 0)
        val tempDay = displayData.listOfForecasts[0].temp
        assertTrue(tempDay.equals(day1.avgTemp.toString()))
    }

    @Test
    fun test_dateToDayConvertor(){
        val date:Long = 1555835807
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = TimeUnit.SECONDS.toMillis(date)
        val day = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
        assertTrue(day.equals("Sunday"))
    }



}