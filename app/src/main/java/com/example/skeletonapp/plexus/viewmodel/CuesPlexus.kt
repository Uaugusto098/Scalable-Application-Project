package com.example.skeletonapp.plexus.viewmodel

import java.util.jar.Attributes

data class CuesPlexus(
    val name: String="",
    val endereco: String="",
) {

    data object ListaCenas {
        val Cenas = listOf(
            CuesPlexus("Cena 1", "/1"),
            CuesPlexus("Cena 2", "/2"),
            CuesPlexus("Cena 3", "/3"),
        )
    }
}
