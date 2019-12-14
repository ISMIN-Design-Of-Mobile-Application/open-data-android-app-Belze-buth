package com.ismin.opendataapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ParkAdapter (private val data: ArrayList<Parking>, private val  clickListener: (Parking) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_park, parent,
            false)


        return ParkViewHolder(row)
    }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            // Populate ViewHolder with data that corresponds to the position in the list
            // which we are told to load
            (holder as ParkViewHolder).bind(data[position], clickListener)
        }



    override fun getItemCount(): Int {
        return this.data.size
    }
}
