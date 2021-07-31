package com.example.githubparser.ui

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.githubparser.R
import com.example.githubparser.databinding.ActivityMainBinding
import com.example.githubparser.ui.repository.search.SearchActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.toolbar.inflateMenu(R.menu.menu_main)
//        binding.toolbar.menu.findItem(R.id.search).expandActionView()

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val search: SearchView =
            MenuItemCompat.getActionView(binding.toolbar.menu.findItem(R.id.search)) as SearchView

        search.setSearchableInfo(
            searchManager.getSearchableInfo(
                ComponentName(
                    this,
                    SearchActivity::class.java
                )
            )
        )
        search.queryHint = resources.getString(R.string.search_title)
    }

    fun getSearchQuery(): String? {
        return intent.extras?.getString("query")
    }
}