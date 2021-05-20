package edu.uw.ss251.dotify

import android.app.Application
import android.widget.Toast

class DotifyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Toast.makeText(this, "Dotify loaded", Toast.LENGTH_SHORT).show()
    }
}