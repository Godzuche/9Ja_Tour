package com.example.a9jatour.ui.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a9jatour.data.model.City
import com.example.a9jatour.databinding.FragmentFavoriteListBinding
import com.example.a9jatour.databinding.ListItemFavoriteBinding

class FavoriteAdapter(val context: Context, var favoriteCityList: MutableList<City>) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ListItemFavoriteBinding.inflate(layoutInflater, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favoriteCity = favoriteCityList[position]
        holder.bindItem(favoriteCity, position)
    }

    override fun getItemCount(): Int = favoriteCityList.size

    inner class FavoriteViewHolder(binding : ListItemFavoriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val imageV_fav_city : ImageView = binding.imvCity
        val nameFavoriteCity : TextView = binding.textCityName

        fun bindItem(favoriteCity: City, position: Int) {
            imageV_fav_city.setImageResource(favoriteCity.imageId)
            nameFavoriteCity.text = favoriteCity.name
        }
    }
}