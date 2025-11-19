package com.alex.concertapp.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.concertapp.domain.model.Concert
import com.alex.concertapp.domain.repository.ConcertRepository
import com.alex.concertapp.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repo: ConcertRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val id: String = checkNotNull(savedStateHandle.get<String>("id"))
    var state by mutableStateOf<UiState<Concert>>(UiState.Loading)
        private set

    init { load() }

    fun load() = viewModelScope.launch {
        state = UiState.Loading
        state = repo.fetchConcert(id).fold(
            onSuccess = { UiState.Success(it) },
            onFailure = { UiState.Error(it.message ?: "Error") }
        )
    }

    fun toggleFavorite() = viewModelScope.launch { repo.toggleFavorite(id) }
}
