package edu.uw.ss251.dotify.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artist(
        val name: String,
        val smallImageURL: String,
        val largeImageURL: String
): Parcelable