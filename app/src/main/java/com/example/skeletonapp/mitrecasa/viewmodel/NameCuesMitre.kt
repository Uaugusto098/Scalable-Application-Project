package com.example.skeletonapp.mitrecasa.viewmodel


data class Cue(val nome:String,val endereco:String)
data object NameCuesMitre{
    val cues=listOf(
    Cue("STANDBY","/1"),
    Cue("PROJETISTAS","/2"),
    Cue("PERSPECTIVAS","/3"),
    Cue("LOCALIZAÇÃO","/4")
    )
}