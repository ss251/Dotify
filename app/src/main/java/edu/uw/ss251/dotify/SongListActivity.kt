package edu.uw.ss251.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.ss251.dotify.databinding.ActivitySongListBinding


class SongListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongListBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.default_header)
        val songs = SongDataProvider.getAllSongs()
        val songListAdapter = SongListAdapter(songs)

        binding.rvSongs.adapter = songListAdapter
    }
}