package edu.uw.ss251.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.uw.ss251.dotify.databinding.ActivitySettingsBinding
import edu.uw.ss251.dotify.databinding.ActivitySongListBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.settings_header)
    }
}