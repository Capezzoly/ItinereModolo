package com.modolo.itineremodolo.campionati

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.modolo.itineremodolo.R
import java.text.SimpleDateFormat

class CalendarioAdapter (val context: Context, val gare: List<Calendario>?) : RecyclerView.Adapter<CalendarioAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gareText: TextView= itemView.findViewById(R.id.txtItemCalendario)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_calendario, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = gare!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gara = gare?.get(position)
        with(holder) {
            if(gara!=null) {
                val dataParsed = SimpleDateFormat("dd-MM-yyyy").parse(gara.data)
                if(dataParsed.time > System.currentTimeMillis()) {
                    gareText.setTypeface(null, Typeface.BOLD)
                }
                gareText.text="["+gara.seq+"] "+gara.circuito+": "+gara.data
            }
        }
    }
}
