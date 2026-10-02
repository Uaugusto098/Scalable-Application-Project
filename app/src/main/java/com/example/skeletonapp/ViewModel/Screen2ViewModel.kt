package com.example.skeletonapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skeletonapp.Model.repository.MainRepository
import com.example.skeletonapp.ViewModel.UiState.Screen1UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException


class Screen2ViewModel(
     val ip: String, val port: Int, private val repository: MainRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(Screen1UiState(ip=ip, porta=port.toString(),destino= "$ip,$port"))
    val uiState: StateFlow<Screen1UiState> = _uiState.asStateFlow()


    fun disparoCenas2(cena: String) {

        viewModelScope.launch {
            _uiState.update { it.copy(enviando = true, erro = null) }
            try {
                repository.DisparoCenas(ip, port, cena)
                _uiState.update { it.copy(enviando = false, ultimaCena = cena) }


            } catch (e: IOException) {

                _uiState.update{it.copy(enviando = false, erro = "IP ou Porta não correspondem ao endereço: ${e.message}")}
            }
        }

    }


}
