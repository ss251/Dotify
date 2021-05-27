package edu.uw.ss251.dotify.manager

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

const val SONG_TAG = "SONG_TAG"

class NotificationSongManager(context: Context) {

    private val workManager: WorkManager = WorkManager.getInstance(context)

    fun getPeriodicSong() {
        if(isFetchSong()) {
            return
        }

        val request = PeriodicWorkRequestBuilder<NotificationSongWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .addTag(SONG_TAG)
            .build()

        workManager.enqueue(request)
    }

    fun getPeriodicSongLowBattery() {
        if(isFetchSong()) {
            return
        }

        val request = PeriodicWorkRequestBuilder<NotificationSongWorker>(2, TimeUnit.DAYS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(true)
                    .build()
            )
            .addTag(SONG_TAG)
            .build()

        workManager.enqueue(request)

    }

    fun stopPeriodicNotify() {
        workManager.cancelAllWorkByTag(SONG_TAG)
    }

    private fun isFetchSong(): Boolean {
        return workManager.getWorkInfosByTag(SONG_TAG).get().any {
            when(it.state) {
                WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
            }
        }
    }

}