package com.jfrausto.musicapp.data
class Repo(private val api: MusicApi = Service.api) {
    suspend fun albums() = api.getAlbums()
    suspend fun album(id: Int) = api.getAlbum(id)
}
