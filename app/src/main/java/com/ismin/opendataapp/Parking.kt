package com.ismin.opendataapp

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Parking (
    @SerializedName("geometry")
    val geometry : Geometry,
    @SerializedName("NOM")
    val NOM : String,
    @SerializedName("SOUS_TYPE")
    val SOUS_TYPE : String,
    @SerializedName("CODE_INSEE")
    val CODE_INSEE : Int): Serializable {


    fun getName (): String {
        return NOM
    }

    fun getType (): String {
        return SOUS_TYPE
    }

    fun getPosition (): Geometry{
        return geometry
    }
}

