package edu.uw.ss251.dotify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.ss251.dotify.databinding.ActivitySongListBinding

class SongListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongListBinding
    private lateinit var currentSong: Song

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.default_header)
        val songs = SongDataProvider.getAllSongs()


        with(binding) {
            val songListAdapter = SongListAdapter(songs)
            currentSong = songs[1]
            tvCurrentSong.text = "${songs[1].title} - ${songs[1].artist}"
            rvSongs.adapter = songListAdapter

            songListAdapter.onSongClickListener = {song: Song ->
                tvCurrentSong.text = "${song.title} - ${song.artist}"
                currentSong = song
            }

            btnShuffle.setOnClickListener {
                songListAdapter.updateSongs(songs.toMutableList().shuffled())
            }

            tvPlayer.setOnClickListener {
                loadPlayerActivity(this@SongListActivity, currentSong)
            }
        }
    }
}