package edu.uw.ss251.dotify.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import edu.uw.ss251.dotify.fragment.StatisticsFragmentArgs
import edu.uw.ss251.dotify.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {
    private val safeArgs: StatisticsFragmentArgs by navArgs()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStatisticsBinding.inflate(inflater)
        val passedSong = safeArgs.song
        val playCount = safeArgs.played
        val songTitle = safeArgs.song.title

        val statsPlayCountTxt = "Play Count: $playCount"

        with(binding){
            statsalbumart.setImageResource(passedSong.smallImageID)
            statsplayed.text = statsPlayCountTxt
        }

        return binding.root
    }

}
