package com.example.jfraustomusicapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CollapsingHeaderPretty(title: String, artist: String, imageUrl: String, height: Dp, contentAlpha: Float) {
    val shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(shape)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(height),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0x00000000),
                            Color(0x802C105C),
                            Color(0xCC2C105C)
                        )
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
                .alpha(contentAlpha),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = title, color = Color.White, style = MaterialTheme.typography.headlineLarge, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Text(text = artist, color = Color(0xFFE4D8FF), style = MaterialTheme.typography.bodyMedium, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = {}, shape = CircleShape, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6F44FF), contentColor = Color.White)) { Text(text = "Play") }
                Button(onClick = {}, shape = CircleShape, colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF6F44FF))) { Text(text = "Shuffle") }
            }
        }
    }
}

@Composable
fun TrackItemPretty(imageUrl: String, title: String, artist: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(22.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(model = imageUrl, contentDescription = null, modifier = Modifier.height(56.dp).clip(RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop)
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.titleLarge, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = artist, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF6B6B6B), maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}
