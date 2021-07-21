package com.example.radioplayer.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.radioplayer.databinding.AdapterRecentsongsBinding
import com.example.radioplayer.model.Nowplaying

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {

    var Nowplayings = mutableListOf<Nowplaying>()

    fun setNowplayingList(Nowplayings: List<Nowplaying>) {
        this.Nowplayings = Nowplayings.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterRecentsongsBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val Nowplaying = Nowplayings[position]
        holder.binding. name.text= Nowplaying.name
        Glide.with(holder.itemView.context).load(Nowplaying.image_url ).into(holder.binding.image)

    }

    override fun getItemCount(): Int {
        return Nowplayings.size
    }
}

class MainViewHolder(val binding: AdapterRecentsongsBinding) : RecyclerView.ViewHolder(binding.root) {

}