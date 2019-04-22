package com.example.go_jektest.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Temprature(
    @SerializedName("location")
    @Expose
    val location: Location? =null,
    @SerializedName("current")
    @Expose
    var current: Current? =null,
    @SerializedName("forecast")
    @Expose
    val forecast: Forecast? = null
)