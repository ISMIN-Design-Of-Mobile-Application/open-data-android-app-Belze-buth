package com.ismin.opendataapp

import java.io.Serializable

data class Data (val data : ArrayList<Parking>) : Serializable {

    fun getParks (): ArrayList<Parking> {
        return data
    }
}