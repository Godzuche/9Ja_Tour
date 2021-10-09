package com.example.a9jatour.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a9jatour.R
import com.example.a9jatour.data.manager.VacationSpots


class FavoriteFragment : Fragment() {

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
        val recyclerView = view?.findViewById<RecyclerView>(R.id.favorite_recycler_view)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView?.layoutManager = layoutManager

        val favoriteAdapter = FavoriteAdapter(context, VacationSpots.favoriteCityList)
        recyclerView?.adapter = favoriteAdapter
        recyclerView?.setHasFixedSize(true)
    }

}