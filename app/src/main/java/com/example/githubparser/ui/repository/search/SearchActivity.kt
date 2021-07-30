package com.example.githubparser.ui.repository.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubparser.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            passSearchQuery(query!!)
        }
    }

    private fun passSearchQuery(query: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("query", query)
        startActivity(intent)
    }
}