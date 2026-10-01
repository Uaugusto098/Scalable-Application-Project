package com.example.skeletonapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.skeletonapp.ViewModel.UiState.Screen1UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


// Arquivo responsável em administrar o estado da tela

class Screen1ViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(Screen1UiState())
    val uiState: StateFlow<Screen1UiState> = _uiState.asStateFlow()

    fun IpChange(novoIp: String) {
        _uiState.update { it.copy(ip = novoIp.trim()) }
    }
    fun PortChange(novaPorta:String){
        _uiState.update{it.copy(porta = novaPorta.filter{c->c.isDigit()})}
    }


}