data class Cue(val nome: String, val endereco: String)

data object NameCues {
    val cues = listOf(
        Cue("INÍCIO", "/1"),
        Cue("ALTHEA", "/2")

    )
}