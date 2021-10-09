package com.example.githubparser.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubparser.R
import com.example.githubparser.ui.repository.ReposFragment

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		if (supportFragmentManager.findFragmentById(R.id.container) == null)
			supportFragmentManager.beginTransaction()
				.replace(R.id.container, ReposFragment())
				.commit()
	}
}
