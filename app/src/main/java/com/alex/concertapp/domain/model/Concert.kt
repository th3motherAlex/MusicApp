package com.alex.concertapp.domain.model

data class Concert(
    val id: String,
    val title: String,
    val artist: String,
    val date: String,
    val venue: String,
    val priceFrom: String,
    val imageUrl: String,
    val isFavorite: Boolean = false
)
