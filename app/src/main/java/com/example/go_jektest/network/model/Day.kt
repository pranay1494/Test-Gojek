package com.example.go_jektest.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("avgtemp_c")
    @Expose
    var avgTemp: Double?
)