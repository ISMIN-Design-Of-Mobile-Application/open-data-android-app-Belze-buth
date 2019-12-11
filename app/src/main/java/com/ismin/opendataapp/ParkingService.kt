package com.ismin.opendataapp


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ParkingService {
    @GET("data/storage/f/2014-06-24T18%3A03%3A28.498Z/carto-parkings.json")
    fun getData(): Call<Data>

}
