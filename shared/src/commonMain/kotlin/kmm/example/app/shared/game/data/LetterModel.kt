package kmm.example.app.shared.game.data

import kmm.example.app.shared.game.data.WordModel.WordDirection


data class LetterModel(
    val letter: Char,
    val words: List<String>,
    val questions: List<String>,
    val indices: List<String>,
    val directions: List<WordDirection>,
)