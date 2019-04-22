package com.example.go_jektest.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name")
    @Expose
    val name: String?= null,
    @SerializedName("region")
    @Expose
    val region: String?=null,
    @SerializedName("country")
    @Expose
    val country: String?=null,
    @SerializedName("lat")
    @Expose
    val lat: Double?=null,
    @SerializedName("lon")
    @Expose
    val lon: Double?=null,
    @SerializedName("tz_id")
    @Expose
    val tzId: String?=null,
    @SerializedName("localtime_epoch")
    @Expose
    val localtimeEpoch: Int?=null,
    @SerializedName("localtime")
    @Expose
    val localtime: String? = null
)