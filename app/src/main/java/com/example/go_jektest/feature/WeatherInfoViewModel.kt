package com.example.go_jektest.feature

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.test.espresso.IdlingRegistry
import com.example.go_jektest.base.BaseSubscriber
import com.example.go_jektest.base.BaseViewModel
import com.example.go_jektest.base.EspressoIdlingResource
import com.example.go_jektest.base.ViewStatus
import com.example.go_jektest.exceptions.Failure
import com.example.go_jektest.repo.WeatherInfoRepo
import com.example.go_jektest.repo.WeatherInfoRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class WeatherInfoViewModel @Inject constructor(val repo: WeatherInfoRepo) : BaseViewModel() {

    private val weatherData = MutableLiveData<WeatherInfoDisplayData>()

    internal fun getWeatherInfo() : LiveData<WeatherInfoDisplayData> = weatherData

    internal fun fetchWeatherData(placeName : String = "Bengaluru",numberOfDays : Int){
        fetchWeatherData(WeatherInfoRepoImpl.WeatherInfoParam(placeName = placeName,numberOfDays = numberOfDays))
    }
    internal fun fetchWeatherData( data : WeatherInfoRepoImpl.WeatherInfoParam){
        viewStatus.postValue(ViewStatus.LOADING)
        repo.fetchWeatherInfo(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { EspressoIdlingResource.increment() }
            .doFinally { EspressoIdlingResource.decrement() }
            .subscribe(object : BaseSubscriber<WeatherInfoDisplayData>() {
                override fun onFailure(failure: Failure) {
                    failure.retry = {fetchWeatherData(data)}
                    handleError(failure)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: WeatherInfoDisplayData) {
                    weatherData.postValue(t)
                    viewStatus.postValue(ViewStatus.SUCCESS)
                }

            })
    }
}