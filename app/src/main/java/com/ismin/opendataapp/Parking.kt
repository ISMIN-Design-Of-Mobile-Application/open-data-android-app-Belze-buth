package com.ismin.opendataapp

import java.io.Serializable

data class Parking ( val geometry : Geometry, val NOM : String, val SOUS_TYPE : String, val CODE_INSEE : Int): Serializable

