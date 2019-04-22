package com.example.go_jektest.di.module

import com.example.go_jektest.repo.WeatherInfoRepo
import com.example.go_jektest.repo.WeatherInfoRepoImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule {

    @Binds
    abstract fun provideWeatherRepo(weatherInfoRepo: WeatherInfoRepoImpl): WeatherInfoRepo

}