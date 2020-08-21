package com.modolo.itineremodolo.championships

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.modolo.itineremodolo.R


class ChampAdapter(
    private val context: Context,
    private val championships: List<Champ>,
    private val champListener: ChampListener
) : RecyclerView.Adapter<ChampAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.txtName)
        val racers: TextView = itemView.findViewById(R.id.txtRacers)
        val races: TextView = itemView.findViewById(R.id.txtRaces)
        val registered: TextView = itemView.findViewById(R.id.viewRegistration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_campionato, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = championships.size

    override fun onBindViewHolder(holder: ViewHolder, index: Int) {
        val championship = championships[index]
        with(holder) {
            name.text = championship.nome
            racers.text = championship.racers.toString() + " RACERS"
            races.text = championship.races.toString() + " RACES"
            //image.setImageDrawable()
            //use championship.ID to count races and racers
            if (championship.registered) {
                registered.setTextColor(Color.WHITE)
                registered.text = "ISCRITTO"
                registered.background =
                    ResourcesCompat.getDrawable(context.resources, R.drawable.subscribed_box, null)
            } else {
                registered.setTextColor(Color.BLACK)
                registered.text = "ISCRIVITI"
                registered.background =
                    ResourcesCompat.getDrawable(context.resources, R.drawable.little_box, null)

            }

            /*registered.setOnClickListener {
                if (registered.text == "ISCRITTO") {
                    registered.setTextColor(Color.BLACK)
                    registered.text = "ISCRIVITI"
                    registered.background =
                        ResourcesCompat.getDrawable(context.resources, R.drawable.little_box, null)
                } else {
                    registered.setTextColor(Color.WHITE)
                    registered.text = "ISCRITTO"
                    registered.background = ResourcesCompat.getDrawable(
                        context.resources,
                        R.drawable.subscribed_box,
                        null
                    )
                }
            }*/

            holder.itemView.setOnClickListener {
                champListener.onChampListener(championship, holder.layoutPosition)
            }
        }

    }

    interface ChampListener {
        fun onChampListener(champ: Champ, position: Int)
    }

}
