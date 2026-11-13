package com.example.skeletonapp.Model.api

import kotlinx.serialization.Serializable

@Serializable
data class ConfigDto(
    val osc: OscDto? = null,
    val botoes: List<BotaoDto> = emptyList(),
    val cores: CoresDto = CoresDto()
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

@Serializable
data class CoresDto(
    val botao: String = "#065741",
    val botaoPressionado: String = "#03271D",
    val texto: String = "#FFFFFF",
    val borda: String = "#000000",
)
