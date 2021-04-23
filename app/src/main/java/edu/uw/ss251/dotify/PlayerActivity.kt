package edu.uw.ss251.dotify

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.ericchee.songdataprovider.Song
import edu.uw.ss251.dotify.databinding.ActivityPlayerBinding
import kotlin.random.Random

private val noOfPlays = Random.nextInt(1000, 1000000)
private var plays = noOfPlays
private const val SONG_KEY = "song"

fun loadPlayerActivity(context: Context, song: Song) {
    val intent = Intent(context, PlayerActivity::class.java)
    val bundle = Bundle().apply {
        putParcelable(SONG_KEY, song)
    }

    intent.putExtras(bundle)
    context.startActivity(intent)
}

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val noPlays = binding.noPlays

        binding.previousButton.setOnClickListener{
            clickPrevious(view)
        }
        binding.playButton.setOnClickListener {
            clickPlay(view)
        }
        binding.nextButton.setOnClickListener {
            clickNext(view)
        }

        noPlays.text = noOfPlays.toString()

        with(binding) {
            ibBack.setOnClickListener {
                loadSongListActivity(this@PlayerActivity)
            }
            val song: Song? = intent.getParcelableExtra<Song>(SONG_KEY)
            songName.text = song?.title
            artistName.text = song?.artist
            val songLength: Int = (songName.height as Int
                    / songName.lineHeight)
            songName.maxLines = songLength
            val artistLength: Int = (artistName.height as Int
                    / artistName.lineHeight)
            artistName.maxLines = artistLength
            noPlays.text = "${noOfPlays.toString()}"
            if (song != null) {
                albumArt.setImageResource(song?.largeImageID)
            }

        }
    }

        fun clickPrevious(view: View) {
            Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
        }

        fun clickPlay(view: View) {
            plays += 1
            binding.noPlays.text = (plays).toString()
        }

        fun clickNext(view: View) {
            Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
        }

        fun clickChangeUser(view: View) {

            val userName = binding.userName
            val userNameEdit = binding.userNameEdit
            val changeUser = binding.changeUser
            userNameEdit.isVisible = true
            if (changeUser.text == "Apply") {
                userNameEdit.isVisible = false
                userName.text = userNameEdit.text
                userName.isVisible = true
                changeUser.text = "Change User"
                return
            }
            userName.isVisible = false
            changeUser.text = "Apply"
        }
}