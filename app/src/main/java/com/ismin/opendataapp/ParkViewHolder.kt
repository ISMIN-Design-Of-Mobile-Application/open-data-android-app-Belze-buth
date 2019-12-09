package com.ismin.opendataapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ParkViewHolder(rootView: View ) : RecyclerView.ViewHolder(rootView) {
    var txvName: TextView
    var txvType: TextView

    init {
        this.txvName = rootView.findViewById(R.id.r_icon_txv_name)
        this.txvType = rootView.findViewById(R.id.r_icon_txv_type)
    }
}