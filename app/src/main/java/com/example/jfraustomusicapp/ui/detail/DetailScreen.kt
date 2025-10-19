package com.example.jfraustomusicapp.ui.detail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jfraustomusicapp.data.Album
import com.example.jfraustomusicapp.data.AlbumRepository
import com.example.jfraustomusicapp.ui.UiState
import com.example.jfraustomusicapp.ui.components.MiniPlayer
import kotlinx.coroutines.launch
import kotlin.math.min
import com.example.jfraustomusicapp.ui.detail.TopOverlayTitlePretty

@Composable
fun DetailScreen(albumId: String) {
    val state = remember { mutableStateOf(UiState<Album>(loading = true)) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(albumId) {
        val r = AlbumRepository.getAlbum(albumId)
        state.value = r.fold(
            onSuccess = { UiState(loading = false, data = it) },
            onFailure = { UiState(loading = false, error = it.message ?: "Error") }
        )
    }

    fun retry() {
        state.value = UiState(loading = true)
        scope.launch {
            val r = AlbumRepository.getAlbum(albumId)
            state.value = r.fold(
                onSuccess = { UiState(loading = false, data = it) },
                onFailure = { UiState(loading = false, error = it.message ?: "Error") }
            )
        }
    }

    val current = state.value.data
    val miniImage = current?.image ?: ""
    val miniTitle = current?.title ?: "Now Playing"
    val miniArtist = current?.artist ?: "..."

    Scaffold(
        bottomBar = { Box(modifier = Modifier.navigationBarsPadding()) { MiniPlayer(imageUrl = miniImage, title = miniTitle, artist = miniArtist) } }
    ) { inner ->
        when {
            state.value.loading -> {
                Box(modifier = Modifier.fillMaxSize().padding(inner), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            }
            state.value.error != null -> {
                Box(modifier = Modifier.fillMaxSize().padding(inner), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(text = "Algo salió mal")
                        Button(onClick = { retry() }) { Text(text = "Retry") }
                    }
                }
            }
            else -> {
                val album = state.value.data!!
                val listState = rememberLazyListState()
                val maxHeader = 320.dp
                val minHeader = 120.dp
                val density = LocalDensity.current
                val maxPx = with(density) { maxHeader.toPx() }
                val minPx = with(density) { minHeader.toPx() }
                val scrollOffsetPx = remember {
                    derivedStateOf {
                        val idx = listState.firstVisibleItemIndex
                        val off = listState.firstVisibleItemScrollOffset
                        if (idx > 0) maxPx - minPx else min(off.toFloat(), maxPx - minPx)
                    }
                }
                val collapse = (scrollOffsetPx.value / (maxPx - minPx)).coerceIn(0f, 1f)
                val headerHeight: Dp = with(density) { (maxPx - collapse * (maxPx - minPx)).toDp() }
                val titleTopAlpha by animateFloatAsState(targetValue = collapse)
                val headerContentAlpha by animateFloatAsState(targetValue = 1f - collapse)

                Box(modifier = Modifier.fillMaxSize().padding(inner)) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 96.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        item { Spacer(modifier = Modifier.height(maxHeader)) }
                        item {
                            Card(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.White),
                                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                                shape = RoundedCornerShape(24.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Text(text = "About this album", style = MaterialTheme.typography.titleLarge)
                                    Text(text = album.description, style = MaterialTheme.typography.bodyMedium)
                                }
                            }
                        }
                        item {
                            Surface(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                color = androidx.compose.ui.graphics.Color(0xFFEDE7FF),
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                Text(
                                    text = "Artist: ${album.artist}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                )
                            }
                        }
                        items(List(10) { i -> "${album.title} • Track ${i + 1}" }) { track ->
                            com.example.jfraustomusicapp.ui.detail.TrackItemPretty(imageUrl = album.image, title = track, artist = album.artist)
                        }
                    }
                    com.example.jfraustomusicapp.ui.detail.CollapsingHeaderPretty(
                        title = album.title,
                        artist = album.artist,
                        imageUrl = album.image,
                        height = headerHeight,
                        contentAlpha = headerContentAlpha
                    )
                    val titleAlpha = 1f - headerContentAlpha

                    TopOverlayTitlePretty(title = album.title, alpha = titleAlpha)


                }
            }
        }
    }
}
