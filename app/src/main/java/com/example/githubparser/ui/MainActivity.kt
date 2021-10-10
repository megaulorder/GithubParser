package com.example.githubparser.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.githubparser.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

	private lateinit var navController: NavController
	private lateinit var appBarConfiguration: AppBarConfiguration

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val navHostFragment = supportFragmentManager.findFragmentById(
			R.id.container
		) as NavHostFragment
		navController = navHostFragment.navController

		// Setup the bottom navigation view with navController
		val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
		bottomNavigationView.setupWithNavController(navController)

		// Setup the ActionBar with navController and 3 top level destinations
		appBarConfiguration = AppBarConfiguration(
			setOf(R.id.reposFragment, R.id.profileFragment)
		)
		val toolbar = findViewById<Toolbar>(R.id.toolbar)
		setSupportActionBar(toolbar)
		toolbar.setupWithNavController(navController, appBarConfiguration)
	}

	override fun onSupportNavigateUp(): Boolean {
		return navController.navigateUp(appBarConfiguration)
	}
}
