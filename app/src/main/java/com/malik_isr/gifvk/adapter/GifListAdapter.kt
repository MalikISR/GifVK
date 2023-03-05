package com.malik_isr.gifvk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.malik_isr.gifvk.Gif
import com.malik_isr.gifvk.R

class GifListAdapter (private val gifs: List<Gif>, private val onItemClick: (Gif) -> Unit) : RecyclerView.Adapter<GifListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gif, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gif = gifs[position]
        Glide.with(holder.itemView.context)
            .load(gif.images.original.url)
            .into(holder.imageView)
        holder.titleView.text = gif.title
        holder.itemView.setOnClickListener { onItemClick(gif) }
    }

    override fun getItemCount(): Int {
        return gifs.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val titleView: TextView = itemView.findViewById(R.id.title_view)
    }

}