package com.example.skeletonapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skeletonapp.Model.osc.OscSender
import com.example.skeletonapp.ViewModel.UiState.ControleOscUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class OscViewModel(private val ip: String, private val port: Int, private val oscSend: OscSender) :
    ViewModel() {

    private val _uiState = MutableStateFlow(ControleOscUiState(destino = "$ip:$port"))
    val uiState: StateFlow<ControleOscUiState> = _uiState.asStateFlow()

    fun OscClick(cena: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(enviando = true, erro = null) }
            try {
                oscSend.send(ip, port, "/$cena", 1, onResult = {})
                _uiState.update { it.copy(enviando = false, ultimaCena = cena) }


            } catch (e: IOException) {
                _uiState.update {
                    it.copy(
                        enviando = false,
                        erro = "Falha no envio da cena: ${e.message}"
                    )
                }
            }
        }

    }


}