package com.example.skeletonapp.ViewModel

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.skeletonapp.Model.osc.OscDestiny
import com.example.skeletonapp.Model.osc.OscSender
import com.example.skeletonapp.Model.repository.MainRepository
import com.example.skeletonapp.Model.repository.SettingRepository
import com.example.skeletonapp.ViewModel.UiState.Screen1UiState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

data class ViewScreen2(
    val destiny: OscDestiny?=null,
    val enviando:Boolean=false,
    val erro:String?=null
){
    val connectionVerify: Boolean get()=destiny!=null
}



@HiltViewModel
class Screen2ViewModel @Inject constructor(
    private val repository: MainRepository,
    private val settingsRepository: SettingRepository

) : ViewModel() {

    private val _uiState = MutableStateFlow(ViewScreen2())
    val uiState: StateFlow<ViewScreen2> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            settingsRepository.destino.collect { destino ->
                _uiState.update{it.copy(destiny = destino)
                }
            }
        }
    }


    fun OscClickScreen2(cena: String) {
        val destino=uiState.value.destiny
        if(destino==null){_uiState.update { it.copy(erro="IP ou Porta inválidos")}
        return}
        viewModelScope.launch {
            _uiState.update { it.copy(enviando = true, erro= null) }
            try {

                _uiState.update { it.copy(enviando = false, erro = "Envio realizado em:${destino}") }

                repository.sendCues(destino,cena)
            }

            catch (e: IOException) {
                _uiState.update {
                    it.copy(
                        enviando= false,
                        erro = "Cena não disparada, erro no envio:${e.message}"

                    )
                }
            }
            catch (e: UnknownHostException){
                _uiState.update { it.copy(enviando=false, erro = "IP inválido, verifique e tente novamente:${e.message} ") }
            }
            finally {
                _uiState.update{it.copy(enviando=false)}
            }
        }

    }


}
