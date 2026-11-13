package com.example.skeletonapp.naeem.ViewModel

import com.example.skeletonapp.Model.api.BotaoDto
import com.example.skeletonapp.Model.osc.OscDestiny

data class Screen1UiStateNaeem(
    val botoes: List<BotaoDto> = NameCuesNaeem.botoes,
    val lastCue: String? = null,
    val sending: Boolean = false,
    val error: String? = null,
    val destiny: OscDestiny? = null,
    val ip: String = "",
    val port: String = ""
) {
    val destino: OscDestiny?
        get() {
            val portaInt = port.toIntOrNull()?.takeIf { it in 1..65535 } ?: return null
            return if (ip.isNotBlank()) OscDestiny(ip, portaInt) else null
        }
    val connectionVerify: Boolean get() = destino != null
}
