package com.example.githubparser.ui.repository

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubparser.data.model.Repo
import com.example.githubparser.databinding.ItemRepositoryBinding

class RepositoryAdapter: RecyclerView.Adapter<ViewHolder>() {

	private val repositories = ArrayList<Repo>()

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): ViewHolder {
		return ViewHolder(
			ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val itemRepository = repositories[position]
		holder.bindRepository(itemRepository)
	}

	override fun getItemCount(): Int = repositories.size

	fun setItems(items: ArrayList<Repo>) {
		Log.d("GithubRepository", "Adapter: repos found: ${items.size}")
		this.repositories.clear()
		this.repositories.addAll(items)
		notifyDataSetChanged()
	}
}

class ViewHolder(
	private val itemBinding: ItemRepositoryBinding
) :
	RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {
	private var repo: Repo? = null

	init {
		itemBinding.root.setOnClickListener(this)
	}

	override fun onClick(v: View) {
		TODO()
	}

	fun bindRepository(repo: Repo) {
		this.repo = repo
		itemBinding.name.text = repo.name
		itemBinding.owner.text = repo.owner.login
	}
}