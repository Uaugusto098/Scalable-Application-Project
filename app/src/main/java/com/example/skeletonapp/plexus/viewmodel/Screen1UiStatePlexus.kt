package com.example.skeletonapp.plexus.viewmodel

import com.example.skeletonapp.Model.api.BotaoDto
import com.example.skeletonapp.Model.osc.OscDestiny

data class Screen1UiStatePlexus(
    val ip: String="",
    val port:String="",
    val destiny: OscDestiny?=null,
    val sending: Boolean=false,
    val error: String="",
    val lastCue: String?=null,
    val botoes: List<BotaoDto> = CuesPlexus.ListaCenas.Cenas.map { BotaoDto(it.name,it.endereco) }
)
{
    val destino: OscDestiny?
        get(){val portaInt=port.toIntOrNull()?.takeIf { it in 1..65535} ?:return null
            return if(ip.isNotBlank()) OscDestiny(ip,portaInt) else null
        }
    val connectionVerify: Boolean get()=destino!=null

}