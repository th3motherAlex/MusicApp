package com.example.jfraustomusicapp.ui

data class UiState<T>(
    val loading: Boolean = true,
    val data: T? = null,
    val error: String? = null
)
