package com.ismin.opendataapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.concurrent.ThreadLocalRandom

class DetailPark : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_park)

        findViewById<TextView>(R.id.d_txv_putname).text="lepiti nom la"
        findViewById<TextView>(R.id.d_txv_putType).text="lepiti type la"
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
