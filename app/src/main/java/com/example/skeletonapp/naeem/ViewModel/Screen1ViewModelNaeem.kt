package com.example.skeletonapp.naeem.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skeletonapp.Althea.view.PaletaRemota
import com.example.skeletonapp.BuildConfig
import com.example.skeletonapp.Model.api.OscDto
import com.example.skeletonapp.Model.repository.ConfigRepository
import com.example.skeletonapp.Model.repository.MainRepository
import com.example.skeletonapp.Model.repository.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class Screen1ViewModelNaeem @Inject constructor(

    private val repository: MainRepository,
    private val settingRepository: SettingRepository,
    private val configRepository: ConfigRepository): ViewModel(){


        private val _uiState= MutableStateFlow(Screen1UiStateNaeem())

         val uiState:StateFlow<Screen1UiStateNaeem> =_uiState.asStateFlow()
        private var ultimoOscRemoto: OscDto?=null

    init {

        viewModelScope.launch {
            configRepository.config.collect { cfg ->
                cfg ?: return@collect
                PaletaRemota.aplicar(cfg.cores)          // <- nova
                cfg.botoes.takeIf { it.isNotEmpty() }?.let { lista ->
                    _uiState.update { it.copy(botoes = lista) }
                }
            }
        }

        viewModelScope.launch {
            settingRepository.destino.first()?.let { salvo ->
                _uiState.update { it.copy(ip = salvo.ipSave, port = salvo.portSave.toString()) }
            }
            while (true) {
                atualizarConfig()
                delay(60_000)
            }
        }
    }

    private suspend fun atualizarConfig() {
        val osc = configRepository.atualizar(BuildConfig.CONFIG_ARQUIVO)?.osc ?: return
        if (osc == ultimoOscRemoto) return
        if (osc.ip.isBlank() || osc.porta !in 1..65535) return
        ultimoOscRemoto = osc
        _uiState.update { it.copy(ip = osc.ip.trim(), port = osc.porta.toString()) }
        DestinySave()
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

    fun OscClick(cena: String) {
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


