package edu.uw.ss251.dotify.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.ericchee.songdataprovider.Song
import edu.uw.ss251.dotify.R
import edu.uw.ss251.dotify.databinding.ActivitySettingsBinding

private const val SONG = "song"
private const val PLAY_KEY = "played"

fun loadSettingsActivity(context: Context, song: Song?, played: Int)= with(context) {
    startActivity(Intent(this,SettingsActivity::class.java).apply {
        putExtra(SONG,song)
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