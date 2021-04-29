package kmm.example.app.shared.game.data

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class CrosswordDataStore(jsonData: String) {
    private val crosswordTable: HashMap<String, CrosswordModel> = HashMap()

    init {
        Json.decodeFromString<List<CrosswordModel>>(jsonData)
            .forEach { crossword -> crosswordTable[crossword.id] = crossword }
    }

    fun getForId(crosswordId: String): CrosswordModel? = crosswordTable[crosswordId]
}