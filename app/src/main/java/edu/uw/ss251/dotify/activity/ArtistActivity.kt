package edu.uw.ss251.dotify.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import edu.uw.ss251.dotify.DotifyApplication
import edu.uw.ss251.dotify.databinding.ActivityArtistBinding
import kotlinx.coroutines.launch
import kotlin.random.Random


fun loadArtistActivity(context: Context, artist: CharSequence)= with(context) {
    startActivity(Intent(this,ArtistActivity::class.java))
}


class ArtistActivity: AppCompatActivity() {

    private val dotifyApp: DotifyApplication by lazy { application as DotifyApplication }
    private val dataRepository by lazy { dotifyApp.dataRepository }
    private lateinit var binding: ActivityArtistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistBinding.inflate(layoutInflater).apply { setContentView(root) }
        with(binding) {
            lifecycleScope.launch {
                val artists = dataRepository.getArtists()
                val randomIndex = Random.nextInt(0, 10)
                artistTitle.text = dataRepository.getArtists().artists[randomIndex].name
                artistImg.load(artists.artists[randomIndex].largeImageURL)
                artistImg.visibility = View.VISIBLE
            }
        }
    }

}