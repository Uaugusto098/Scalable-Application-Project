package com.example.skeletonapp.ViewModel.UiState

data class Screen1UiState(
    val ultimaCena: String? = null,
    val enviando: Boolean = false,
    val erro: String? = null,
    val destino: String? = null,
    val ip: String = "",
    val porta: String = ""
) {

    val portaInt: Int? get() = porta.toIntOrNull()?.takeIf { it in 1..346789 }
    val verificacaoConexao: Boolean get() = ip.isNotBlank() && porta != null


}