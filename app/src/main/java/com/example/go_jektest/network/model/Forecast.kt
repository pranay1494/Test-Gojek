package com.example.go_jektest.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Forecast(@SerializedName("forecastday")
                    @Expose
                    val  day: ArrayList<Forecastday>? = null)