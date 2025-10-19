package com.jfrausto.musicapp.data
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object Service {
    val api: MusicApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://music.juanfrausto.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MusicApi::class.java)
    }
}
