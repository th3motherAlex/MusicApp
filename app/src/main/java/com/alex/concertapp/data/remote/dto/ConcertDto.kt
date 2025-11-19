package com.alex.concertapp.data.remote.dto

import com.squareup.moshi.Json
import com.alex.concertapp.domain.model.Concert

data class ConcertDto(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "artist") val artist: String,
    @Json(name = "date") val date: String,
    @Json(name = "venue") val venue: String,
    @Json(name = "price_from") val priceFrom: String,
    @Json(name = "image") val imageUrl: String
)

fun ConcertDto.toDomain() = Concert(id, title, artist, date, venue, priceFrom, imageUrl)
