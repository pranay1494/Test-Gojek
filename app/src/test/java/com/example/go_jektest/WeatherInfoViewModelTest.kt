package com.example.go_jektest

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.go_jektest.feature.WeatherInfoDisplayData
import com.example.go_jektest.feature.WeatherInfoViewModel
import com.example.go_jektest.repo.WeatherInfoRepo
import com.example.go_jektest.repo.WeatherInfoRepoImpl
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class WeatherInfoViewModelTest {

    @Mock
    private lateinit var repo: WeatherInfoRepo

    private lateinit var viewModel: WeatherInfoViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

    @Before
    fun init() {
        viewModel = Mockito.spy(WeatherInfoViewModel(repo))
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
    }

    @Test
    fun verifyApiResponseSuccess() {
        val placeName = "jbh"
        val numberOfDays = 1
        val responseObject = WeatherInfoDisplayData().apply {
            currentLocation= placeName
        }

        val requestObject = WeatherInfoRepoImpl.WeatherInfoParam(placeName = "",numberOfDays = 1)

        `when`(repo.fetchWeatherInfo(requestObject)).thenReturn(Observable.just(responseObject))
        viewModel.fetchWeatherData(requestObject)
        Assert.assertTrue(
            (viewModel.getWeatherInfo().value as WeatherInfoDisplayData).currentLocation.equals(
                "jbh",
                true
            )
        )
    }

    @Test
    fun verifyHandleErrorCalledWhenErrorOccurs(){
        val requestObject = WeatherInfoRepoImpl.WeatherInfoParam(placeName = "",numberOfDays = 1)
        `when`(repo.fetchWeatherInfo(requestObject)).thenReturn(Observable.error(Exception()))
        viewModel.fetchWeatherData(requestObject)
        verify(viewModel).handleError(any())
    }
}