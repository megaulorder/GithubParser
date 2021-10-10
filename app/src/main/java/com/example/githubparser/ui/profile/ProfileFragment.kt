package com.example.githubparser.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubparser.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val binding = ProfileFragmentBinding.inflate(inflater, container, false)

		Log.d("GithubRepository", "Profile fragment opened")

		return binding.root
	}
}