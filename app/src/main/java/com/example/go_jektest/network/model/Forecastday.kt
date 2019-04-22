package com.example.go_jektest.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Forecastday(
    @SerializedName("date")
    @Expose
    val date: String?=null,
    @SerializedName("date_epoch")
    @Expose
    val dateEpoch: Long?=null,
    @SerializedName("day")
    @Expose
    val day: Day?=null
)