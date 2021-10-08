package com.example.a9jatour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.a9jatour.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var toolbar: MaterialToolbar
    private lateinit var navController: NavController
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Initialize Views
        toolbar = binding.appBarMain.toolbar
        navigationView = binding.navView
        drawerLayout = binding.drawerLayout

        //Get NavHostFragment and NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        //Define AppBarConfiguration: Connect DrawerLayout with Navigation Component
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        //Connect Toolbar with NavController
        toolbar.setupWithNavController(navController, appBarConfiguration)

        //Connect NavigationView with NavController
        navigationView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        if (drawerLayout.isOpen)
            drawerLayout.close()
        else
            super.onBackPressed()
    }
}