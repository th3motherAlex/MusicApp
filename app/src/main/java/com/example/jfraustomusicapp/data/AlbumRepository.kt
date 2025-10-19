package com.example.jfraustomusicapp.data

object AlbumRepository {
    suspend fun getAlbums(): Result<List<Album>> = runCatching {
        RetrofitInstance.api.getAlbums()
    }.recoverCatching {
        FakeAlbums
    }

    suspend fun getAlbum(id: String): Result<Album> = runCatching {
        RetrofitInstance.api.getAlbum(id)
    }.recoverCatching {
        FakeAlbums.first { it.id == id }
    }
}
