package edu.uw.ss251.dotify.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.uw.ss251.dotify.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAboutBinding.inflate(inflater)
        val version = "1.0.0"
        with(binding) {
            appVersion.text = version
        }

        return binding.root
    }

}