package com.example.githubparser.ui.repository

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubparser.data.model.Repository
import com.example.githubparser.databinding.ItemRepositoryBinding

class RepositoryAdapter(private val listener: RepositoryItemListener) :
    RecyclerView.Adapter<ViewHolder>() {

    interface RepositoryItemListener {
        fun onClickedRepository()
    }

    private val repositories = ArrayList<Repository>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemRepository = repositories[position]
        holder.bindRepository(itemRepository)
    }

    override fun getItemCount(): Int = repositories.size

    fun setItems(items: ArrayList<Repository>) {
        this.repositories.clear()
        this.repositories.addAll(items)
        notifyDataSetChanged()
    }
}

class ViewHolder(
    private val itemBinding: ItemRepositoryBinding,
    private val listener: RepositoryAdapter.RepositoryItemListener
) :
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