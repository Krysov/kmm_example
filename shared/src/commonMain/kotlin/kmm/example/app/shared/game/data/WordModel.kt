package kmm.example.app.shared.game.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WordModel(
    @SerialName("i") val index: String,
    @SerialName("x") val startAtX: Short,
    @SerialName("y") val startAtY: Short,
    @SerialName("d") val direction: String,
    @SerialName("q") val question: String,
    @SerialName("a") val answer: String,
) {

    data class WordDirection(
        val x: Short,
        val y: Short,
    )
}