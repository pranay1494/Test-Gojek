package com.example.go_jektest.di.module.builder

import com.example.go_jektest.feature.SplashActivity
import com.example.go_jektest.feature.WeatherInfoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherInfoActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(WeatherInfoFragmentBuilder::class))
    abstract fun contributeWeatherinfoActivity() : SplashActivity

}