package com.capstone.cultour.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.cultour.data.api.explore.RecommendedPlaceItem
import com.capstone.cultour.data.api.home.PlacesItem
import com.capstone.cultour.databinding.RecommendationItemBinding

class RecommendationAdapter(private val listRecommendedPlaceItem: List<RecommendedPlaceItem>) :
    RecyclerView.Adapter<RecommendationAdapter.ModelViewHolder>() {
    class ModelViewHolder(var binding: RecommendationItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val binding = RecommendationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {


        val placeCard = listRecommendedPlaceItem[position]

        val rvPlace: TextView = holder.binding.placename
        val rvRating: TextView = holder.binding.tvRating
        val rvCategory: TextView = holder.binding.tvCategory

        rvPlace.text = placeCard.name
        rvRating.text = placeCard.rating.toString()
        rvCategory.text = placeCard.category

    }
    override fun getItemCount(): Int = listRecommendedPlaceItem.size


}