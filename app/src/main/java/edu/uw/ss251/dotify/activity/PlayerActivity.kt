package edu.uw.ss251.dotify.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ericchee.songdataprovider.Song
import edu.uw.ss251.dotify.DotifyApplication
import edu.uw.ss251.dotify.databinding.ActivityPlayerBinding
import kotlin.random.Random


const val SONG_KEY = "song"
private const val PLAY_COUNT = "plays"

fun loadPlayerActivity(context: Context, song: Song) {
    val intent = Intent(context, PlayerActivity::class.java)
    val bundle = Bundle().apply {
        putParcelable(SONG_KEY, song)
    }

    intent.putExtras(bundle)
    context.startActivity(intent)
}

class PlayerActivity : AppCompatActivity() {
    private var noOfPlays = Random.nextInt(1000, 1000000)
    private lateinit var binding: ActivityPlayerBinding

    private val dotifyApp: DotifyApplication by lazy { application as DotifyApplication }
    private val dataRepository by lazy { dotifyApp.dataRepository }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            with(savedInstanceState) {
                noOfPlays = getInt(PLAY_COUNT, -1)
            }
        }

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
        binding.artistName.setOnClickListener {
            showArtist(view)
        }

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

            artistName.setOnClickListener {
                val artist = artistName.text
                loadArtistActivity(this@PlayerActivity, artist)
            }

        }

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(PLAY_COUNT, noOfPlays)
        super.onSaveInstanceState(outState)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

        fun clickPrevious(view: View) {
            Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
        }

        fun clickPlay(view: View) {
            noOfPlays += 1
            binding.noPlays.text = (noOfPlays).toString()
        }

        fun clickNext(view: View) {
            Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
        }

        fun clickSettings(view: View) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        fun showArtist(view: View) {
            val intent = Intent(this, ArtistActivity::class.java)
            startActivity(intent)
        }

        /*suspend fun showArtist(view: View): View.OnClickListener? {
            with(binding){
                lifecycleScope.launchWhenCreated {
                    val artist = dataRepository.getArtist()
                }
            }
            val artist = dataRepository.getArtist()
            return binding.root
        }*/

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