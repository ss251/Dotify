package edu.uw.ss251.dotify

import android.app.Application
import android.widget.Toast
import edu.uw.ss251.dotify.repository.DataRepository


class DotifyApplication: Application() {
    lateinit var dataRepository: DataRepository

    override fun onCreate() {
        super.onCreate()

        Toast.makeText(this, "Dotify loaded", Toast.LENGTH_SHORT).show()
    }
}