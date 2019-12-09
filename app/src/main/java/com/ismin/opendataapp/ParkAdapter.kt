package com.ismin.opendataapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ParkAdapter (private val data: Data) :
RecyclerView.Adapter<ParkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_park, parent,
            false)


        return ParkViewHolder(row)
    }

    override fun onBindViewHolder(viewholder: ParkViewHolder, position: Int) {
        val parkings = this.data.getParks()
        val name = parkings[position].getName()
        val type = parkings[position].getType()

        viewholder.txvName.text = name
        viewholder.txvType.text = type
    }

    override fun getItemCount(): Int {
        return this.data.getParks().size
    }
}
