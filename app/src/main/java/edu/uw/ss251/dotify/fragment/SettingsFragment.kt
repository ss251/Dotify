package edu.uw.ss251.dotify.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uw.ss251.dotify.DotifyApplication
import edu.uw.ss251.dotify.NOTIFICATIONS_ENABLED
import edu.uw.ss251.dotify.NavGraphDirections
import edu.uw.ss251.dotify.R
import edu.uw.ss251.dotify.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private val safeArgs: SettingsFragmentArgs by navArgs()
    private val navController by lazy {findNavController()}
    private val application by lazy { context?.applicationContext as DotifyApplication }
    private val settings by lazy { application.settings }
    private val notificationSongManager by lazy { application.notificationSongManager }

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

            notify.isChecked = settings.getBoolean(NOTIFICATIONS_ENABLED, false)

            notify.setOnCheckedChangeListener { _, isChecked ->
                settings.edit {
                    putBoolean(NOTIFICATIONS_ENABLED, isChecked)
                }
                if (isChecked) {
                    notificationSongManager.getPeriodicSong()
                } else {
                    notificationSongManager.stopPeriodicNotify()
                }
            }
        }

        return binding.root
    }
}
