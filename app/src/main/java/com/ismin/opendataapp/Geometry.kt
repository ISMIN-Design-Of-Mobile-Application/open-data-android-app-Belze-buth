package com.ismin.opendataapp

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Geometry (
    @SerializedName("type")
    val type: String,
    @SerializedName("coordinates")
    val coordinates: ArrayList<Float>): Serializable


