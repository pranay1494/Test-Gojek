package com.example.go_jektest.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Current(@SerializedName("temp_c")
                   @Expose
                   var temprature:Double?)