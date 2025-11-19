package com.alex.concertapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.concertapp.domain.model.Concert
import com.alex.concertapp.domain.repository.ConcertRepository
import com.alex.concertapp.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ConcertRepository
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<List<Concert>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Concert>>> = _state

    init { refresh() }

    fun refresh() = viewModelScope.launch {
        _state.value = UiState.Loading
        _state.value = repo.fetchConcerts().fold(
            onSuccess = { UiState.Success(it) },
            onFailure = { UiState.Error(it.message ?: "Error desconocido") }
        )
    }
}
