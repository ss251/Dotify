package edu.uw.ss251.dotify

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import edu.uw.ss251.dotify.manager.NotificationManager
import edu.uw.ss251.dotify.manager.NotificationSongManager
import edu.uw.ss251.dotify.repository.DataRepository

const val SETTINGS_KEY = "Dotify Settings"
const val NOTIFICATIONS_ENABLED = "Notify Status"

class DotifyApplication: Application() {

    lateinit var dataRepository: DataRepository
    lateinit var settings: SharedPreferences
    lateinit var notificationManager: NotificationManager
    lateinit var notificationSongManager: NotificationSongManager

    override fun onCreate() {
        super.onCreate()

        dataRepository = DataRepository()
        this.notificationManager = NotificationManager(this)
        this.notificationSongManager = NotificationSongManager(this)
            this.settings = getSharedPreferences(SETTINGS_KEY, Context.MODE_PRIVATE)

        if(settings.getBoolean(NOTIFICATIONS_ENABLED, false)){
            notificationSongManager.getPeriodicSong()
        }

        Toast.makeText(this, "Dotify loaded", Toast.LENGTH_SHORT).show()
    }
}