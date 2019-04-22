package com.example.go_jektest.di.component

import com.example.go_jektest.GojekApp
import com.example.go_jektest.di.module.BaseModule
import com.example.go_jektest.di.module.NetworkModule
import com.example.go_jektest.di.module.RepoModule
import com.example.go_jektest.di.module.builder.WeatherInfoActivityBuilder
import com.example.go_jektest.di.module.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,BaseModule::class,RepoModule::class,ViewModelModule::class,NetworkModule::class,WeatherInfoActivityBuilder::class))
interface AppComponent {
    fun inject(app : GojekApp)
}