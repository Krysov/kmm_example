package kmm.example.app.shared.game.data


data class LetterModel(
    val letter: Char,
    val words: List<String>,
    val questions: List<String>,
    val indicies: List<Short>,
    val directions: List<WordDirection>,
) {

    data class WordDirection(
        val x: Short,
        val y: Short,
    )
}