package com.modolo.itineremodolo.classifiche

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.modolo.itineremodolo.R

class ClassificaTeamAdapter (val context: Context, val classificaTeam: List<Classificateam>?) : RecyclerView.Adapter<ClassificaTeamAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val teamText: TextView = itemView.findViewById(R.id.txtItemTeamRankingNome)
        val punteggio: TextView = itemView.findViewById(R.id.txtItemTeamRankingPunti)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_ranking_t, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = classificaTeam!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = classificaTeam?.get(position)
        with(holder) {
            if(team!=null) {
                teamText.text=team.team+": "+team.auto
                punteggio.text=team.punti
            }
        }
    }
}