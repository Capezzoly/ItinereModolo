package com.modolo.itineremodolo.campionati

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.modolo.itineremodolo.R

class ImpostazioniAdapter (val context: Context, val impostazioni: List<Impostazionigioco>?) : RecyclerView.Adapter<ImpostazioniAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val impostazioniText: TextView = itemView.findViewById(R.id.txtItemImpostazioni)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_impostazioni, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = impostazioni!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val impostazione = impostazioni?.get(position)
        with(holder) {
            if(impostazione!=null) {
                impostazioniText.text=impostazione.tipo + ": " + impostazione.valore
            }
        }
    }
}