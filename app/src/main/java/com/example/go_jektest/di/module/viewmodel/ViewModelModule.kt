package com.example.go_jektest.di.module.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.go_jektest.di.module.viewmodel.ViewModelFactory
import com.example.go_jektest.feature.WeatherInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WeatherInfoViewModel::class)
    abstract fun bindsSplashViewModel(weatherInfoViewModel: WeatherInfoViewModel): ViewModel

}