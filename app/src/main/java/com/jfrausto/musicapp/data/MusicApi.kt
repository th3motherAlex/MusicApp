package com.jfrausto.musicapp.data
import retrofit2.http.GET
import retrofit2.http.Path
interface MusicApi {
    @GET("api/albums") suspend fun getAlbums(): List<Album>
    @GET("api/albums/{id}") suspend fun getAlbum(@Path("id") id: Int): Album
}
