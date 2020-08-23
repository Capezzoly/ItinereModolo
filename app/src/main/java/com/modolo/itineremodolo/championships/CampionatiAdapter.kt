package com.modolo.itineremodolo.championships

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.modolo.itineremodolo.R

class CampionatiAdapter(val context: Context, val campionati: List<Campionati>?,val campionatiListener: CampionatiListener) : RecyclerView.Adapter<CampionatiAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.txtName)
        val iscritti: TextView = itemView.findViewById(R.id.txtIscritti)
        val gare: TextView = itemView.findViewById(R.id.txtGare)
        val imgCampionato: ImageView = itemView.findViewById(R.id.imgCampionato)
        //val registered: TextView = itemView.findViewById(R.id.viewRegistration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_campionato, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = campionati!!.size

    override fun onBindViewHolder(holder: ViewHolder, index: Int) {
        val campionato = campionati?.get(index)
        with(holder) {
            if(campionato!=null) {
                name.text = campionato.nome.toUpperCase()
                iscritti.text = campionato.pilotiiscritti.size.toString() + " ISCRITTI"
                gare.text = campionato.calendario.size.toString() + " GARE"
                val imageInput  = context.assets.open(campionato.logo)
                val image = Drawable.createFromStream(imageInput,null)
                imgCampionato.setImageDrawable(image)

                holder.itemView.setOnClickListener {
                    campionatiListener.onCampionatiListener(campionato, holder.layoutPosition)
                }
            }
        }
    }

    interface CampionatiListener {
        fun onCampionatiListener(campionato: Campionati, position: Int)
    }

}

