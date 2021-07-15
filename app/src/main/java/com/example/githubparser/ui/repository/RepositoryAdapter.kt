package com.example.githubparser.ui.repository

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubparser.data.model.Repository
import com.example.githubparser.databinding.ItemRepositoryBinding

class RepositoryAdapter(private val repositories: ArrayList<Repository>) :
    RecyclerView.Adapter<ViewHolder>() {

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
}

class ViewHolder(private val itemBinding: ItemRepositoryBinding) :
    RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {
    private var repository: Repository? = null

    init {
        itemBinding.root.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        Log.d("RecyclerView", "CLICK!")
    }

    fun bindRepository(repository: Repository) {
        this.repository = repository
        itemBinding.name.text = repository.name
    }
}