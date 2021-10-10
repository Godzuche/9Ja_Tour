package com.example.a9jatour.ui.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a9jatour.data.model.City
import com.example.a9jatour.databinding.ListItemFavoriteBinding

class FavoriteAdapter(private val context: Context, private var favCityList: MutableList<City>) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ListItemFavoriteBinding.inflate(layoutInflater, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val city = favCityList[position]
        holder.bindItem(city, position)
    }

    override fun getItemCount(): Int = favCityList.size

    inner class FavoriteViewHolder(binding : ListItemFavoriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentPositon: Int = -1
        private var currentCity: City? = null

        val imageV_fav_city : ImageView = binding.imvCity
        val nameFavoriteCity : TextView = binding.textCityName

        fun bindItem(city: City, position: Int) {
            imageV_fav_city.setImageResource(city.imageId)
            nameFavoriteCity.text = city.name

            this.currentCity = city
            this.currentPositon = position
        }
    }
}