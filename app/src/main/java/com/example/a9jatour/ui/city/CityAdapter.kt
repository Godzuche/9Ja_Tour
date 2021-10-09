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
import com.example.a9jatour.data.manager.VacationSpots
import com.example.a9jatour.data.model.City

class CityAdapter(val context: Context, var cityList: ArrayList<City>) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    val tag = this::class.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {

        Log.i(tag, "onCreateViewHolder: ViewHolder created")

        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_city_list, parent, false)
        return CityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {

        Log.i(tag, "onBindViewHolder: position: $position")

        val city = cityList[position]
        holder.setData(city, position)
        holder.setListener()
    }

    override fun getItemCount() = cityList.size

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private var currentPosition: Int = -1
        private var currentCity: City? = null

        val imageCity: ImageView = itemView.findViewById(R.id.imv_city)
        val textCityName = itemView.findViewById<TextView>(R.id.text_name_city)
        val iconFavorite =  itemView.findViewById<ImageView>(R.id.icon_favorite)
        val iconDelete = itemView.findViewById<ImageView>(R.id.icon_delete)

        private val icFavoriteFilledImage = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_favorite_filled, null)
        private val icFavoriteBotheredImage = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_favorite_bordered, null)

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

        fun setListener() {
            iconDelete.setOnClickListener(this@CityViewHolder)
            iconFavorite.setOnClickListener(this@CityViewHolder)
        }

        override fun onClick(v: View?) {
            when(v!!.id) {
                R.id.icon_delete -> deleteItem()
                R.id.icon_favorite -> addToFavorite()
            }
        }

        private fun addToFavorite() {

        }

        private fun deleteItem() {

        }
    }
}