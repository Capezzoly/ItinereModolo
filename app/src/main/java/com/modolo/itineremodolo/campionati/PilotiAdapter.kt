package com.modolo.itineremodolo.campionati

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.modolo.itineremodolo.R

class PilotiAdapter (val context: Context, val piloti: List<Pilotiiscritti>?) : RecyclerView.Adapter<PilotiAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pilotiText: TextView = itemView.findViewById(R.id.txtItemPiloti)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_piloti, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = piloti!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pilota = piloti?.get(position)
        with(holder) {
            if(pilota!=null) {
                pilotiText.text="- "+pilota.nome+" ("+pilota.team+"): "+pilota.auto
            }
        }
    }
}