package com.example.githubparser.ui.repos.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.githubparser.R
import com.example.githubparser.databinding.RepoDetailFragmentBinding
import com.example.githubparser.ui.repos.ReposFragment.Companion.AVATAR_URL_KEY
import com.example.githubparser.ui.repos.ReposFragment.Companion.DESCRIPTION_KEY
import com.example.githubparser.ui.repos.ReposFragment.Companion.NAME_KEY

class RepoDetailFragment : Fragment() {
	private lateinit var binding: RepoDetailFragmentBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = RepoDetailFragmentBinding.inflate(inflater, container, false)

		Log.d("GithubRepository", "Repo detail fragment opened")

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		bindViewModel()
	}

	private fun bindViewModel() {
		with(binding) {
			val repo = arguments
			name.text = repo?.getString(NAME_KEY)
			description.text = repo?.getString(DESCRIPTION_KEY)

			Glide.with(avatar)
				.load(repo?.getString(AVATAR_URL_KEY))
				.placeholder(R.drawable.ic_avatar_placeholder)
				.into(avatar)
		}
	}
}