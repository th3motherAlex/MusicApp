package com.jfrausto.musicapp.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun AlbumCard(imageUrl: String, title: String, artist: String, onClick: ()->Unit, onPlay: ()->Unit) {
    Box(Modifier.width(260.dp).height(180.dp).clip(RoundedCornerShape(24.dp)).clickable { onClick() }) {
        AsyncImage(model = imageUrl, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
        Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color(0x99000000)))).padding(16.dp)) {
            Column(Modifier.align(Alignment.BottomStart)) {
                Text(title, color = Color.White, fontWeight = FontWeight.Bold)
                Text(artist, color = Color.White.copy(alpha=.9f), style = MaterialTheme.typography.labelMedium)
            }
            Surface(color = Color.White, shape = CircleShape, modifier = Modifier.size(40.dp).align(Alignment.BottomEnd), onClick = onPlay) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("▷") }
            }
        }
    }
}

@Composable
fun RecentlyPlayedItem(imageUrl: String, title: String, artist: String, onClick:()->Unit) {
    Surface(shape = RoundedCornerShape(16.dp), tonalElevation = 2.dp, shadowElevation = 2.dp,
        modifier = Modifier.fillMaxWidth().clickable { onClick() }) {
        Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(model = imageUrl, contentDescription = null,
                modifier = Modifier.size(48.dp).clip(RoundedCornerShape(8.dp)), contentScale = ContentScale.Crop)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.SemiBold)
                Text(artist, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = {}) { Text("⋯") }
        }
    }
}

@Composable
fun MiniPlayer(imageUrl: String, title: String, artist: String, isPlaying: Boolean, onToggle: ()->Unit) {
    Surface(color = Color(0xFF2A1746), shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp), tonalElevation = 8.dp) {
        Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(model = imageUrl, contentDescription = null,
                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)), contentScale = ContentScale.Crop)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(title, color = Color.White, fontWeight = FontWeight.SemiBold)
                Text(artist, color = Color.White.copy(alpha=.8f), style = MaterialTheme.typography.labelSmall)
            }
            Surface(shape = CircleShape, color = Color.White, modifier = Modifier.size(44.dp), onClick = onToggle) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text(if (isPlaying) "❚❚" else "▷") }
            }
        }
    }
}
