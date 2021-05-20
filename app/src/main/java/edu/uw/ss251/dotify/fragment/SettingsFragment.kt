package edu.uw.ss251.dotify.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uw.ss251.dotify.NavGraphDirections
import edu.uw.ss251.dotify.R
import edu.uw.ss251.dotify.fragment.SettingsFragmentArgs
import edu.uw.ss251.dotify.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private val safeArgs: SettingsFragmentArgs by navArgs()
    private val navController by lazy {findNavController()}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {val binding = FragmentSettingsBinding.inflate(inflater)
        val song = safeArgs.song
        val played = safeArgs.played

        with(binding) {
            btnProfile.setOnClickListener {
                navController.navigate(R.id.profileFragment)
            }

            btnStats.setOnClickListener {
                navController.navigate(NavGraphDirections.actionDispatchStats(song, played))
            }

            btnAbout.setOnClickListener {
                navController.navigate(R.id.aboutFragment)
            }
        }

        return binding.root
    }
}
