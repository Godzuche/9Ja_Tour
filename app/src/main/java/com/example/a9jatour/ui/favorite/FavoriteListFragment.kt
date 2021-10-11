package com.example.a9jatour.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a9jatour.R
import com.example.a9jatour.data.manager.VacationSpots
import com.example.a9jatour.data.manager.VacationSpots.favoriteCityList
import com.example.a9jatour.data.model.City
import com.google.android.material.snackbar.Snackbar
import java.util.*

class FavoriteFragment : Fragment() {

    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var favoriteCityList: MutableList<City>
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite_list, container, false)
        setupRecyclerView(view)
        return view
    }

    private fun setupRecyclerView(view: View?) {
        val context = requireContext()
        favoriteCityList = VacationSpots.favoriteCityList
        view?.let { recyclerView = view.findViewById(R.id.favorite_recycler_view) }

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager

        favoriteAdapter = FavoriteAdapter(context, favoriteCityList)
        recyclerView.adapter = favoriteAdapter
        recyclerView.setHasFixedSize(true)

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            targetViewHolder: RecyclerView.ViewHolder,
        ): Boolean {
            val fromPosition = viewHolder.adapterPosition
            val toPosition = targetViewHolder.adapterPosition
            Collections.swap(favoriteCityList, fromPosition, toPosition)
            recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            //Called when the item is swiped
            val position = viewHolder.adapterPosition
            val deletedCity = favoriteCityList[position]

            deleteItem(position)
            updateCityList(deletedCity, false)

            Snackbar.make(recyclerView, "Deleted", Snackbar.LENGTH_LONG)
                .setAction("UNDO") {
                    undoDelete(position, deletedCity)
                    updateCityList(deletedCity, true)
                }
                .show()
        }
    })

    private fun deleteItem(position: Int) {
        favoriteCityList.removeAt(position)
        favoriteAdapter.notifyItemRemoved(position)
        favoriteAdapter.notifyItemRangeChanged(position, favoriteCityList.size)
    }

    private fun updateCityList(deletedCity: City, isFavorite: Boolean) {
        val cityList = VacationSpots.cityList!!
        val position = cityList.indexOf(deletedCity)
        cityList[position].isFavorite = isFavorite
    }

    private fun undoDelete(position: Int, deletedCity: City) {
        favoriteCityList.add(position, deletedCity)
        favoriteAdapter.notifyItemInserted(position)
        favoriteAdapter.notifyItemRangeChanged(position, favoriteCityList.size)
    }
}