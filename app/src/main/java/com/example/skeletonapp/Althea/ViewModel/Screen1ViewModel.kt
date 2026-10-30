package com.example.skeletonapp.Althea.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skeletonapp.Model.repository.MainRepository
import com.example.skeletonapp.Model.repository.SettingRepository
import com.example.skeletonapp.Althea.ViewModel.UiState.Screen1UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


// Arquivo responsável em administrar o estado da tela

@HiltViewModel
class Screen1ViewModel @Inject constructor(private val repository: MainRepository,private val settingRepository: SettingRepository) : ViewModel() {


    private val _uiState = MutableStateFlow(Screen1UiState())
    val uiState: StateFlow<Screen1UiState> = _uiState.asStateFlow()

    init{
        viewModelScope.launch{ settingRepository.destino.first()?.let {salvo ->
            _uiState.update{it.copy(ip = salvo.ipSave, port = salvo.portSave.toString())}
        }}
    }



    fun IpChange(newIp: String) {
        _uiState.update { it.copy(ip = newIp.trim()) }
        DestinySave()
    }
    fun PortChange(newPort: String) {
        _uiState.update { it.copy(port = newPort.filter { c -> c.isDigit() }) }
        DestinySave()
    }
    fun DestinySave(){
        val destino= uiState.value.destino?:return
        viewModelScope.launch { settingRepository.savePrefsDestiny(destino) }
    }

    fun OscClickScreen1(cena: String) {
        val destino=uiState.value.destino?:return
        viewModelScope.launch {
            _uiState.update { it.copy(sending = true, error = null) }
            try {

                _uiState.update { it.copy(sending = true, error = "Envio realizado") }
                repository.sendCues(destino,cena)
            }

         catch (e: IOException) {
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







