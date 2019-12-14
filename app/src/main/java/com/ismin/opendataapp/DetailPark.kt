package com.ismin.opendataapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.concurrent.ThreadLocalRandom
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class DetailPark : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_park)
        val extras = intent.extras
        val nom = extras!!.getString("NOM")
        val type = extras.getString("TYPE")
        findViewById<TextView>(R.id.d_txv_putname).text=nom
        findViewById<TextView>(R.id.d_txv_putType).text=type
        findViewById<TextView>(R.id.d_txv_place).text="Il reste actuellement " +randomInt(0,250).toString()+" places !! "
        findViewById<TextView>(R.id.d_txv_année).text="Parking crée en 19"+randomInt(0,99).toString()
                    // comme y a pas assez de data on s'arrange :p





            val closeButton = findViewById<Button>(R.id.d_but_close).setOnClickListener { view ->
            finish()
        }
    }
    fun randomInt(rangeFirstNum:Int, rangeLastNum:Int) :Int {
        val randomInteger = ThreadLocalRandom.current().nextInt(rangeFirstNum,rangeLastNum)
        return(randomInteger)
    }
}
