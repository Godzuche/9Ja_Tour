package com.example.a9jatour.ui.city

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a9jatour.R
import com.example.a9jatour.data.model.City
import kotlin.concurrent.fixedRateTimer

class CityAdapter(val context: Context, var cityList: ArrayList<City>) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    val tag = this::class.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {

        Log.i(tag, "onCreateViewHolder: ViewHolder created")

        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_city, parent, false)
        return CityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {

        Log.i(tag, "onBindViewHolder: position: $position")

        val city = cityList[position]
        holder.setData(city, position)
    }

    override fun getItemCount() = cityList.size

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var currentPosition: Int = -1
        private var currentCity: City? = null

        val imageCity: ImageView = itemView.findViewById(R.id.image_city)
        val textCityName = itemView.findViewById<TextView>(R.id.text_city_name)
        val iconFavorite =  itemView.findViewById<ImageView>(R.id.icon_favorite)
        val iconDelete = itemView.findViewById<ImageView>(R.id.icon_delete)

        private val icFavoriteFilledImage = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_favorite_filled, null)
        private val icFavoriteBotheredImage = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_favorite_bordered, null)

        /*init {
            iconFavorite.setOnClickListener {
                if (currentCity?.isFavorite!!)
                    cityList[currentPosition].isFavorite = false
                else
                    currentCity?.isFavorite = true
                notifyDataSetChanged()
            }
        }*/

        fun setData(city: City, position: Int) {
            imageCity.setImageResource(city.imageId)
            textCityName.text = city.name

            if (city.isFavorite)
                iconFavorite.setImageDrawable(icFavoriteFilledImage)
            else
                iconFavorite.setImageDrawable(icFavoriteBotheredImage)

            this.currentPosition = position
            this.currentCity = city
        }
    }
}