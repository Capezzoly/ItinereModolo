package com.modolo.itineremodolo.classifiche

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.modolo.itineremodolo.R
import com.modolo.itineremodolo.campionati.PilotiAdapter
import com.modolo.itineremodolo.campionati.Pilotiiscritti

class ClassificaPilotiAdapter(val context: Context, val classificaPiloti: List<Classificapiloti>) : RecyclerView.Adapter<ClassificaPilotiAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pilotiText: TextView = itemView.findViewById(R.id.txtItemPilotiRanking)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_ranking_p, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = classificaPiloti!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pilota = classificaPiloti?.get(position)
        with(holder) {
            if(pilota!=null) {
                pilotiText.text="("+pilota.punti+") "+pilota.nome+" ("+pilota.team+"): "+pilota.auto
            }
        }
    }
}