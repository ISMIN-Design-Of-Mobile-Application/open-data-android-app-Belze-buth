package com.ismin.opendataapp

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Data (
    @SerializedName("docs")
    val docs : ArrayList<Parking>) : Serializable