package edu.uw.ss251.dotify.repository

import edu.uw.ss251.dotify.model.Artists
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class DataRepository {

    private val artistService = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArtistService::class.java)
    suspend fun getArtists(): Artists = artistService.getArtists()
}


interface ArtistService {

    @GET("echeeUW/codesnippets/master/allartists.json")
    suspend fun getArtists(): Artists

}