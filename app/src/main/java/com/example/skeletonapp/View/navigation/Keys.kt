package com.example.skeletonapp.View.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

//Arquivo responsavel em armazenar os IDs das telas e suas respectivas informações.
@Serializable data object ScreenKey1: NavKey
@Serializable data class ScreenKey2(val ip:String,val port: Int): NavKey



