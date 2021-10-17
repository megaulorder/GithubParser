package com.example.githubparser.ui.repos

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubparser.R
import com.example.githubparser.db.Repo
import com.example.githubparser.databinding.ItemRepoBinding

class ReposViewHolder(binding: ItemRepoBinding, listener: ReposAdapter.RepoItemListener) :
	RecyclerView.ViewHolder(binding.root) {

	private val name: TextView = binding.name
	private val description: TextView = binding.description
	private val avatar: ImageView = binding.avatar

	private lateinit var repo: Repo

	init {
		itemView.setOnClickListener {
			listener.onRepoClicked(binding, repo)
			Log.d("GithubRepository", "ReposViewHolder: Clicked ${repo.fullName}")
		}
	}

	fun showRepoData(repo: Repo) {
		this.repo = repo
		name.text = repo.fullName

		Glide.with(avatar)
			.load(repo.owner.avatarUrl)
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
}
