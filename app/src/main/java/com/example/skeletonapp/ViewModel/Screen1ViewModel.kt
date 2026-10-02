package com.example.skeletonapp.ViewModel

import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skeletonapp.Model.osc.OscSender
import com.example.skeletonapp.Model.repository.MainRepository
import com.example.skeletonapp.ViewModel.UiState.Screen1UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException


// Arquivo responsável em administrar o estado da tela

class Screen1ViewModel(private val repository: MainRepository) : ViewModel() {


    private val _uiState = MutableStateFlow(Screen1UiState())
    val uiState: StateFlow<Screen1UiState> = _uiState.asStateFlow()


    fun IpChange(novoIp: String) {
        _uiState.update { it.copy(ip = novoIp.trim()) }
    }
    fun PortChange(novaPorta:String){
        _uiState.update{it.copy(porta = novaPorta.filter{c->c.isDigit()})}
    }

    fun OscClick(cena:String){
        try {
            viewModelScope.launch{

                _uiState.update { it.copy(enviando = true, erro = null) }

                repository.DisparoCenas(_uiState.value.ip, _uiState.value.portaInt?:0, cena)
            }
        }
        catch(e: Exception){
            _uiState.update { it.copy(enviando = false, erro = "Cena não disparada, erro no envio:${e.message}") }
        }
    }



}