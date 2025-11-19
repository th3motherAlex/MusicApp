package com.alex.concertapp.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.alex.concertapp.ui.common.UiState

@Composable
fun DetailsScreen(
    onBuyClick: (String) -> Unit,
    vm: DetailsViewModel = hiltViewModel()
) {
    when (val s = vm.state) {
        UiState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        is UiState.Error -> Text("Error: " + s.message)
        is UiState.Success -> {
            val c = s.data
            Column(Modifier.padding(16.dp)) {
                AsyncImage(model = c.imageUrl, contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(220.dp))
                Spacer(Modifier.height(12.dp))
                Text(c.title, style = MaterialTheme.typography.headlineSmall)
                Text(c.artist, style = MaterialTheme.typography.bodyMedium)
                Text("${c.date} • ${c.venue}")
                Text("From ${c.priceFrom}")
                Spacer(Modifier.height(16.dp))
                Button(onClick = { onBuyClick(c.id) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Buy Tickets")
                }
            }
        }
    }
}
