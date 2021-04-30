package kmm.example.app.shared.game.data


data class LetterModel(
    val letter: Char,
    val indices: List<Short>,
    val words: List<WordModel>,
)