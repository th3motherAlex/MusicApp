package com.jfrausto.musicapp.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jfrausto.musicapp.data.Album
import com.jfrausto.musicapp.ui.components.MiniPlayer
import com.jfrausto.musicapp.ui.state.UiState

@Composable
fun DetailScreen(vm: DetailViewModel, albumId: Int, onBack: () -> Unit) {
    val state by vm.state.collectAsState()
    var playing by remember { mutableStateOf(false) }
    LaunchedEffect(albumId) { vm.load(albumId) }

    Box(Modifier.fillMaxSize()) {
        when (state) {
            is UiState.Loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
            is UiState.Error -> Text("Error: ${(state as UiState.Error).message}", color = Color.Red, modifier = Modifier.align(Alignment.Center))
            is UiState.Success -> {
                val a = (state as UiState.Success<Album>).data
                LazyColumn(contentPadding = PaddingValues(bottom = 96.dp)) {
                    item {
                        Box {
                            AsyncImage(model = a.image, contentDescription = null, modifier = Modifier.fillMaxWidth().height(260.dp))
                            Box(Modifier.matchParentSize().background(Brush.verticalGradient(listOf(Color(0x805E35B1), Color.Transparent))))
                            Column(Modifier.padding(16.dp).align(Alignment.BottomStart)) {
                                Text(a.title, color = Color.White, fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.headlineSmall)
                                Text(a.artist, color = Color.White.copy(alpha=.9f))
                                Row(Modifier.padding(top = 12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                    FilledTonalButton(onClick = { playing = true }) { Text("Play") }
                                    OutlinedButton(onClick = { /* shuffle */ }) { Text("Shuffle") }
                                }
                            }
                        }
                    }
                    item {
                        Card(modifier = Modifier.padding(16.dp), shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {
                            Column(Modifier.padding(16.dp)) {
                                Text("About this album", fontWeight = FontWeight.Bold)
                                Spacer(Modifier.height(6.dp))
                                Text(a.description)
                                Spacer(Modifier.height(10.dp))
                                AssistChip(onClick = {}, label = { Text("Artist: ${a.artist}") })
                            }
                        }
                    }
                    items((1..10).map { "Track $it" }) { t ->
                        ListItem(
                            headlineContent = { Text("${a.title} • $t") },
                            supportingContent = { Text(a.artist) },
                            leadingContent = {
                                AsyncImage(model = a.image, contentDescription = null,
                                    modifier = Modifier.size(48.dp).clip(RoundedCornerShape(8.dp)))
                            }
                        )
                        Divider()
                    }
                }
            }
        }
        Surface(Modifier.align(Alignment.BottomCenter)) {
            MiniPlayer("https://i.imgur.com/0KFBHTB.jpeg", "Tales of Ithiria", "Haggard", playing) { playing = !playing }
        }
    }
}
