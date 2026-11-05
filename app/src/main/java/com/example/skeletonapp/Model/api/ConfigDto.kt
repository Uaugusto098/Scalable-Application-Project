package com.example.skeletonapp.Model.api

import kotlinx.serialization.Serializable

@Serializable
data class ConfigDto(
    val osc: OscDto? = null,
    val botoes: List<BotaoDto> = emptyList(),
)

@Serializable
data class OscDto(val ip: String, val porta: Int)

@Serializable
data class BotaoDto(
    val nome: String,
    val endereco: String,
    val larguraDp: Int = 200,
    val alturaDp: Int = 70,
)