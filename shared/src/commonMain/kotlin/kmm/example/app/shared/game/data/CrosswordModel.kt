package kmm.example.app.shared.game.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
data class CrosswordModel(
    val id: String,
    val words: List<WordModel>,
) {

    @Transient
    val letters: List<LetterModel> = listOf()

    fun getLetterAt(x: Short, y: Short): LetterModel {
        throw NotImplementedError()
    }

}