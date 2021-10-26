package com.example.githubparser.ui.profile

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.example.githubparser.R
import com.example.githubparser.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		setHasOptionsMenu(true)

		val binding = ProfileFragmentBinding.inflate(inflater, container, false)

		Log.d("GithubRepository", "Profile fragment opened")

		return binding.root
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.profile_menu, menu)
		return
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		AlertDialog.Builder(requireContext())
			.setTitle("Console")
			.setMessage(R.string.debug)
			.setPositiveButton(getString(R.string.ok)) { _, _ -> }
			.create()
			.show()

		Log.d("GithubRepository", "Profile fragment: debug console clicked")

		return true
	}
}
