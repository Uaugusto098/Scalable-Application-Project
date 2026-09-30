package com.example.skeletonapp.View.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

//Arquivo responsavel em armazenar os IDs das telas e suas respectivas informações.
@Serializable data object screen1: NavKey
@Serializable data class screen2(val ip:String,val porta: Int): NavKey



