package com.alex.concertapp.data.remote

import com.alex.concertapp.data.remote.dto.ConcertDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ConcertApi {
    @GET("concerts")
    suspend fun getConcerts(): List<ConcertDto>

    @GET("concerts/{id}")
    suspend fun getConcert(@Path("id") id: String): ConcertDto
}
