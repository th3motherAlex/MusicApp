package com.alex.concertapp.domain.repository

import com.alex.concertapp.domain.model.Concert
import kotlinx.coroutines.flow.Flow

interface ConcertRepository {
    suspend fun fetchConcerts(): Result<List<Concert>>
    suspend fun fetchConcert(id: String): Result<Concert>
    suspend fun toggleFavorite(id: String): Result<Unit>
    fun observeFavorites(): Flow<List<Concert>>
}
