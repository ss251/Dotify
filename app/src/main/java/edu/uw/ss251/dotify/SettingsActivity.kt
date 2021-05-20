package edu.uw.ss251.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.Song
import edu.uw.ss251.dotify.databinding.ActivitySettingsBinding
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import edu.uw.ss251.dotify.databinding.ActivitySongListBinding

private const val SONG_KEY = "song"
private const val PLAY_KEY = "played"

fun loadSettingsActivity(context: Context, song: Song, played: Int)= with(context) {
    startActivity(Intent(this,SettingsActivity::class.java).apply {
        putExtra(SONG_KEY,song)
        putExtra(PLAY_KEY,played)
    })
}


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val navController by lazy {findNavController(R.id.navHost)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater).apply {setContentView(root)}

        navController.setGraph(R.navigation.nav_graph, intent.extras)
        setupActionBarWithNavController(navController)
        title = getString(R.string.settings_header)
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return true
    }

}