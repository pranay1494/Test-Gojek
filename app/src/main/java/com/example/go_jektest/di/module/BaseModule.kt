package com.example.go_jektest.di.module

import android.app.Application
import android.content.Context
import com.example.go_jektest.GojekApp
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule(val app : GojekApp) {

    @Singleton
    @Provides
    fun provideApp() : Application = app

    @Singleton
    @Provides
    fun provideAppContext() : Context = app

    @Singleton
    @Provides
    fun provideGson() : Gson = Gson()
}