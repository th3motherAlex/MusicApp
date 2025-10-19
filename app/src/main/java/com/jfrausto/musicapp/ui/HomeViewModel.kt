package com.jfrausto.musicapp.ui
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jfrausto.musicapp.data.Album
import com.jfrausto.musicapp.data.Repo
import com.jfrausto.musicapp.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: Repo = Repo()) : ViewModel() {
    private val _state = MutableStateFlow<UiState<List<Album>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Album>>> = _state
    init { refresh() }
    fun refresh() = viewModelScope.launch {
        _state.value = UiState.Loading
        runCatching { repo.albums() }
            .onSuccess { _state.value = UiState.Success(it) }
            .onFailure { _state.value = UiState.Error(it.message ?: "Error") }
    }
}
