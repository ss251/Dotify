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

        binding.previousButton.setOnClickListener {
            clickPrevious(view)
        }
        binding.playButton.setOnClickListener {
            clickPlay(view)
        }
        binding.nextButton.setOnClickListener {
            clickNext(view)
        }

        with(binding) {


            noPlays.text = noOfPlays.toString()

            with(binding) {

                val song: Song? = intent.getParcelableExtra(SONG_KEY)
                songName.text = song?.title
                artistName.text = song?.artist
                noPlays.text = noOfPlays.toString()
                if (song != null) {
                    albumArt.setImageResource(song.largeImageID)
                }

                btnSettings.setOnClickListener {
                    if (song != null) {
                        loadSettingsActivity(this@PlayerActivity, song, noOfPlays)
                    }
                }

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

        fun clickSettings(view: View) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        //username and edittext (hw1 and 2)
        /*fun clickChangeUser(view: View) {

            val userName = binding.userName
            val userNameEdit = binding.userNameEdit
            val changeUser = binding.changeUser
            if (changeUser.text == getString(R.string.apply_change_user)) {
                userNameEdit.isVisible = false
                userName.isVisible = true
                userName.text = userNameEdit.text
                changeUser.text = getString(R.string.change_user)
                return
            }
            userName.isVisible = false
            userNameEdit.isVisible = true

            changeUser.text = getString(R.string.apply_change_user)
        }*/

    }