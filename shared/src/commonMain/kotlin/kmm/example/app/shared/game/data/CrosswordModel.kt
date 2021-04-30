package kmm.example.app.shared.game.data

import kotlinx.serialization.Serializable


@Serializable
data class CrosswordModel(
    val id: String,
    val words: List<WordModel>,
) {
    val letters: Map<String, LetterModel> by lazy { getLetterMap(words) }
    fun getLetterAt(x: Short, y: Short): LetterModel? = letters[getLetterTableKey(x, y)]
}

private fun getLetterMap(fromWords: List<WordModel>): Map<String, LetterModel> {
    val result: MutableMap<String, LetterModel> = HashMap(fromWords.count())
    fromWords
        .sortedBy { it.index }
        .forEach { unpackWord(it, result) }
    return result
}

private fun unpackWord(word: WordModel, intoLetterTable: MutableMap<String, LetterModel>) {
    word.answer.forEachIndexed { index, letter ->
        val (x, y) = word.getLetterPosition(index)
        val key = getLetterTableKey(x, y)
        val existingLetter = intoLetterTable[key]
        existingLetter?.let { existing ->
            if (letter != existing.letter) throw IllegalArgumentException(
                """Letter '${letter}' does not match letter '${existing.letter}' at [${x};${y}] 
                        for the intersecting words: ${
                    existing.words.plus(word).map { it.answer }
                }""".trimIndent()
            )
        }
        val inductedLetter =
            existingLetter?.copy(
                words = existingLetter.words.plus(word),
                indices = existingLetter.indices.plus(index.toShort()),
            ) ?: LetterModel(letter, listOf(index.toShort()), listOf(word))
        intoLetterTable[key] = inductedLetter
    }
}

private fun getLetterTableKey(x: Short, y: Short): String = "${x}_${y}"
