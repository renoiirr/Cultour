package com.capstone.cultour.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.cultour.data.api.home.PlacesItem
import com.capstone.cultour.databinding.CardItemBinding
import com.capstone.cultour.databinding.RecommendationItemBinding

class RatingAdapter(private val listPlaces: List<PlacesItem>) :
    RecyclerView.Adapter<RatingAdapter.WisataViewHolder>() {

    var onItemClickListener: ((PlacesItem) -> Unit)? = null
    class WisataViewHolder(var binding: RecommendationItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataViewHolder {
        val binding = RecommendationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WisataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WisataViewHolder, position: Int) {


        val placeCard = listPlaces[position]

        val rvPlace: TextView = holder.binding.placename
        val rvRating: TextView = holder.binding.tvRating
        val rvCategory: TextView = holder.binding.tvCategory

        rvPlace.text = placeCard.name
        rvRating.text = placeCard.rating.toString()
        rvCategory.text = placeCard.category

    }
    override fun getItemCount(): Int = listPlaces.size


}
