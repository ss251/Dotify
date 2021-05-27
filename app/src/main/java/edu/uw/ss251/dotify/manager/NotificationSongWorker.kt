package edu.uw.ss251.dotify.manager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.ss251.dotify.DotifyApplication

class NotificationSongWorker (
    private val context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    private val application by lazy { context.applicationContext as DotifyApplication }
    private val notificationManager by lazy { application.notificationManager }

    override suspend fun doWork(): Result {
        val song = SongDataProvider.getAllSongs().random()
        notificationManager.songNotify(song)
        return Result.success()
    }
}