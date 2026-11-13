package com.example.skeletonapp.naeem.ViewModel

import com.example.skeletonapp.Model.api.BotaoDto

// Botões padrão do Naeem: aparecem até o naeem.json chegar (ou se não houver internet).
// A ORDEM define a posição na tela: os 3 primeiros vão na linha de cima,
// os 3 seguintes na linha de baixo. Endereços iguais aos do app antigo.
object NameCuesNaeem {
    val botoes = listOf(
        BotaoDto("STANDBY", "/1"),
        BotaoDto("CONCEITO", "/2"),
        BotaoDto("LANÇAMENTO", "/3"),
        BotaoDto("RUN", "/4"),
        BotaoDto("EXPERIENCE", "/5"),
        BotaoDto("AUXILIAR", "/6"),
    )
}
