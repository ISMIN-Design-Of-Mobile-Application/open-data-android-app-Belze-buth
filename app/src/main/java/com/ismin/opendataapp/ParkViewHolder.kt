package com.ismin.opendataapp

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_park.view.*

class ParkViewHolder(rootView: View ) : RecyclerView.ViewHolder(rootView)  {

    var txvName: TextView
    var txvType: TextView

    init {
        this.txvName = rootView.findViewById(R.id.r_icon_txv_name)
        this.txvType = rootView.findViewById(R.id.r_icon_txv_type)
        //this.setOnClickListener
    }
    fun bind(park: Parking, clickListener: (Parking) -> Unit) {
        itemView.r_icon_txv_name.text = park.NOM
        itemView.r_icon_txv_type.text = park.getType()
        itemView.setOnClickListener { clickListener(park)}
    }
}