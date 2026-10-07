package com.example.skeletonapp.ViewModel.UiState

data class Screen1UiState(
    val lastCue: String? = null,
    val sending: Boolean = false,
    val error: String? = null,
    val destiny: String? = null,
    val ip: String = "",
    val port: String = ""
) {

    val portInt: Int? get() = port.toIntOrNull()?.takeIf { it in 1..346789 }
    val connectionVerify: Boolean get() = ip.isNotBlank() && port!= null


}