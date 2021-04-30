package kmm.example.app.shared.game.data


data class LetterModel(
    val letter: Char,
    val indices: List<Short>,
    val words: List<WordModel>,
) {
    fun getLetterPosition(): Pair<Short, Short> =
        words[0].getLetterPosition(indices[0].toInt())

    fun getWordsLedByThis(): List<WordModel> =
        words.filterIndexed { i, _ -> indices[i].toInt() == 0 }
}