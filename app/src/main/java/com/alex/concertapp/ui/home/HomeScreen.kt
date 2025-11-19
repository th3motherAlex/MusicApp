package com.alex.concertapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.alex.concertapp.domain.model.Concert
import com.alex.concertapp.ui.common.ErrorView
import com.alex.concertapp.ui.common.UiState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder

@Composable
fun HomeScreen(
    onConcertClick: (String) -> Unit,
    vm: HomeViewModel = hiltViewModel()
) {
    val state by vm.state.collectAsStateWithLifecycle()
    when (state) {
        UiState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        is UiState.Error -> ErrorView((state as UiState.Error).message) { vm.refresh() }
        is UiState.Success -> ConcertList((state as UiState.Success<List<Concert>>).data, onConcertClick)
    }
}

@Composable
private fun ConcertList(data: List<Concert>, onClick: (String) -> Unit) {
    LazyColumn {
        items(data) { c -> ConcertCard(c, onClick = { onClick(c.id) }) }
    }
}

@Composable
fun ConcertCard(concert: Concert, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.large
    ) {
        Column(Modifier.padding(16.dp)) {
            AsyncImage(
                model = concert.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text(concert.title, style = MaterialTheme.typography.titleLarge)
            Text(concert.artist, style = MaterialTheme.typography.bodyMedium)
            Text("${concert.date} • ${concert.venue}", style = MaterialTheme.typography.bodySmall)
            Text("From ${concert.priceFrom}", style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Buy Tickets")
            }
        }
    }
}
