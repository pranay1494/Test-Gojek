package com.example.go_jektest.feature

import android.support.v4.app.Fragment
import com.example.go_jektest.base.BaseActivity

class SplashActivity : BaseActivity(){
    override fun fragment(): Fragment = WeatherInfoFragment.newInstance()
}