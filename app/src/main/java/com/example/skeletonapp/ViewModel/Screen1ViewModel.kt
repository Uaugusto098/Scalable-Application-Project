package com.example.skeletonapp.ViewModel

import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skeletonapp.Model.osc.OscSender
import com.example.skeletonapp.Model.repository.MainRepository
import com.example.skeletonapp.ViewModel.UiState.Screen1UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton


// Arquivo responsável em administrar o estado da tela

@HiltViewModel
class Screen1ViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {


    private val _uiState = MutableStateFlow(Screen1UiState())
    val uiState: StateFlow<Screen1UiState> = _uiState.asStateFlow()


    fun IpChange(newIp: String) {
        _uiState.update { it.copy(ip = newIp.trim()) }
    }

    fun PortChange(newPort: String) {
        _uiState.update { it.copy(port = newPort.filter { c -> c.isDigit() }) }
    }

    fun OscClickScreen1(cena: String) {

        viewModelScope.launch {
            _uiState.update { it.copy(sending = true, error = null) }
            try {

                _uiState.update { it.copy(sending = false, error = null) }

                repository.DisparoCenas(_uiState.value.ip, _uiState.value.portInt ?: 0, cena)
            }

         catch (e: UnknownHostException) {
            _uiState.update {
                it.copy(
                    sending = false,
                    error = "Cena não disparada, erro no envio:${e.message}"
                )
            }
        }
            finally {
                _uiState.update{it.copy(sending=false)}
            }
    }

    }
}







