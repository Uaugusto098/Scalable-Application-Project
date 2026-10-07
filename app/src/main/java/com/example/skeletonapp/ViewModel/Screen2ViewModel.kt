package com.example.skeletonapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skeletonapp.Model.repository.MainRepository
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



@HiltViewModel(assistedFactory = Screen2ViewModel.Factory::class)
class Screen2ViewModel @AssistedInject constructor(
    @Assisted private val ip: String,
    @Assisted private val port: Int,
    private val repository: MainRepository

) : ViewModel() {


    @AssistedFactory
    interface Factory{
        fun create(ip:String,port: Int): Screen2ViewModel
    }


    private val _uiState = MutableStateFlow(Screen1UiState(ip=ip, port=port.toString(),destiny= "$ip,$port"))
    val uiState: StateFlow<Screen1UiState> = _uiState.asStateFlow()


    fun OscClickScreen2(cue: String) {

        viewModelScope.launch {
            _uiState.update { it.copy(sending = true, error = null) }
            try {
                repository.DisparoCenas(ip, port, cue)
                _uiState.update { it.copy(sending = false, lastCue = cue) }

            } catch (e: IOException) {

                _uiState.update{it.copy(sending = false, error = "IP ou Porta não correspondem ao endereço: ${e.message}")}
            }
        }

    }


}
