package com.example.go_jektest

import android.app.Activity
import android.app.Application
import com.example.go_jektest.di.component.DaggerAppComponent
import com.example.go_jektest.di.module.BaseModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class GojekApp : Application(),HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    val appComponent by lazy {
        DaggerAppComponent.builder().baseModule(BaseModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

}