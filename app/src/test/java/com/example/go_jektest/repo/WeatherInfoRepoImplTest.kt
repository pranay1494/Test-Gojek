package com.example.go_jektest.repo

import com.example.go_jektest.feature.WeatherInfoDisplayData
import com.example.go_jektest.network.WeatherApi
import com.example.go_jektest.network.model.Temprature
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class WeatherInfoRepoImplTest {

    @Mock
    private lateinit var api: WeatherApi

    @Test
    fun testApiDataSuccess(){

        val responseObject = Temprature()

        Mockito.`when`(api.getTemperature("","",1)).thenReturn(Observable.just(responseObject))

        val testObserver = TestObserver<Temprature>()

        val result = api.getTemperature("","",1)
        result.subscribe(testObserver)
        testObserver.assertComplete()
    }

    @Test
    fun testApiDataError(){
        val error = Exception()

        Mockito.`when`(api.getTemperature("","",1)).thenReturn(Observable.error(error))

        val testObserver = TestObserver<Temprature>()

        val result = api.getTemperature("","",1)
        result.subscribe(testObserver)
        testObserver.assertError(error)
    }
}