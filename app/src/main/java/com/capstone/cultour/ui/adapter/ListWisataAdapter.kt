package com.capstone.cultour.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.cultour.R
import com.capstone.cultour.data.dummy.dummyData

class ListWisataAdapter(private val listWisataAdapter: ArrayList<dummyData>): RecyclerView.Adapter<ListWisataAdapter.WisataViewHolder>() {
    class WisataViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val tvName = itemView.findViewById(R.id.placename) as TextView
        val tvDetail = itemView.findViewById(R.id.address) as TextView
        val imgLokasi = itemview.findViewById(R.id.iv_card) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item,parent,false)
        return WisataViewHolder(view)
    }

    override fun getItemCount() = listWisataAdapter.size

    override fun onBindViewHolder(holder: WisataViewHolder, position: Int) {
        holder.tvName.text = listWisataAdapter[position].place
        holder.tvDetail.text = listWisataAdapter[position].description
        Glide.with(holder.itemView.context)
            .load(listWisataAdapter[position].photo)
            .into(holder.imgLokasi)

    }
}