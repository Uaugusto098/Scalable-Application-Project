package com.example.skeletonapp.ViewModel.UiState

data class ControleOscUiState(
    val ultimaCena: String? = null,
    val enviando: Boolean = false,
    val erro: String? = null,
    val destino: String? = null
)