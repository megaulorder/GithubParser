package com.example.githubparser.ui.repos

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

class RepoViewHolder(view: View, listener: ReposAdapter.RepoItemListener?) :
	RecyclerView.ViewHolder(view) {
	private val name: TextView = view.findViewById(R.id.name)
	private val description: TextView = view.findViewById(R.id.description)
	private val avatar: ImageView = view.findViewById(R.id.avatar)

	private var repo: Repo? = null

	init {
		itemView.setOnClickListener {
			listener?.onRepoClicked()
			Log.d("GithubRepository", "Clicked $it")
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
		fun create(parent: ViewGroup, listener: ReposAdapter.RepoItemListener?): RepoViewHolder {
			val view = LayoutInflater.from(parent.context)
				.inflate(R.layout.item_repo, parent, false)
			return RepoViewHolder(view, listener)
		}
	}
}
