package com.example.githubparser.ui.repository

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubparser.R
import com.example.githubparser.data.model.Repo

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
	private val name: TextView = view.findViewById(R.id.name)
	private val description: TextView = view.findViewById(R.id.description)
	private val avatar: ImageView = view.findViewById(R.id.avatar)

	private var repo: Repo? = null

	init {
		view.setOnClickListener {
			Log.d("Github Repository", "Clicked $it !")
		}
	}

	fun showRepoData(repo: Repo) {
		this.repo = repo
		name.text = repo.fullName

		Glide.with(avatar)
			.load(repo.owner.avatar)
			.placeholder(R.drawable.ic_avatar_placeholder)
			.into(avatar)

		// if the description is missing, hide the TextView
		var descriptionVisibility = View.GONE
		if (repo.description != null) {
			description.text = repo.description
			descriptionVisibility = View.VISIBLE
		}
		description.visibility = descriptionVisibility
	}

	companion object {
		fun create(parent: ViewGroup): RepoViewHolder {
			val view = LayoutInflater.from(parent.context)
				.inflate(R.layout.item_repository, parent, false)
			return RepoViewHolder(view)
		}
	}
}
