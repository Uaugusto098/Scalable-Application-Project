package com.example.skeletonapp.plexus.viewmodel

import com.example.skeletonapp.Model.api.OscDto
import com.example.skeletonapp.Model.osc.OscDestiny
import com.example.skeletonapp.Model.repository.ConfigRepository
import com.example.skeletonapp.Model.repository.MainRepository
import com.example.skeletonapp.Model.repository.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow



class Screen1ViewModelPlexus constructor(

    private val repository: MainRepository,
    private val settingRepository: SettingRepository,
    private val configRepository: ConfigRepository
)
{

    private val _uiState= MutableStateFlow(Screen1UiStatePlexus())
    val uiState: StateFlow<Screen1UiStatePlexus> = _uiState.asStateFlow()

    private var ultimoOscRemote: OscDto? = null




}

