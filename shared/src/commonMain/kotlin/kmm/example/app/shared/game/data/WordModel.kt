package kmm.example.app.shared.game.data

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


@Serializable
data class WordModel(
    @SerialName("i") val index: String,
    @SerialName("x") val startAtX: Short,
    @SerialName("y") val startAtY: Short,
    @SerialName("d") val direction: WordDirection,
    @SerialName("q") val question: String,
    @SerialName("a") val answer: String,
) {

    fun getLetterPosition(atLetterIndex: Int): Pair<Short, Short> = Pair(
        (startAtX + atLetterIndex * direction.x).toShort(),
        (startAtY + atLetterIndex * direction.y).toShort(),
    )

    @Serializable(with = WordDirectionSerializer::class)
    data class WordDirection(
        val compass: Char,
        val x: Short,
        val y: Short,
    )

    private object WordDirectionSerializer : KSerializer<WordDirection> {
        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor("WordModelDirection", PrimitiveKind.CHAR)

        override fun serialize(encoder: Encoder, value: WordDirection) =
            encoder.encodeChar(value.compass)

        override fun deserialize(decoder: Decoder): WordDirection =
            when (val compass = decoder.decodeChar()) {
                'N' -> WordDirection(compass, 0, -1)
                'E' -> WordDirection(compass, 1, 0)
                'S' -> WordDirection(compass, 0, 1)
                'W' -> WordDirection(compass, -1, 0)
                else -> throw SerializationException(
                    "WordDirection for compass val:\"${compass}\" not supported!"
                )
            }
    }

}