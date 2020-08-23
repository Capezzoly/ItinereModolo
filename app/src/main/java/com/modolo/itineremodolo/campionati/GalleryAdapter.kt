package com.modolo.itineremodolo.campionati

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.modolo.itineremodolo.R

class GalleryAdapter (val context: Context, val nomiImmagini: List<String>?) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgG: ImageView = itemView.findViewById(R.id.imgGallery)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_gallery, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = nomiImmagini!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val img = nomiImmagini?.get(position)
        with(holder) {
            val imageInput  = context.assets.open("$img")
            val image = Drawable.createFromStream(imageInput,null)
            imgG.setImageDrawable(image)
        }
    }
}