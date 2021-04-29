package kmm.example.app.shared.game.data

import kotlin.test.Test
import kotlin.test.assertEquals


class DataStoreUnitTest {

    @Test
    fun testDataStore() {
        val store = CrosswordDataStore(testData)
        val crossword: CrosswordModel = store.getForId("test_puzzle") as CrosswordModel
        val words = crossword.words
        assertEquals(3, words.count())
        assertEquals("1", words[0].index)
        assertEquals("2", words[1].index)
        assertEquals("3", words[2].index)
        assertEquals(4 + 14 + 5, crossword.letters.count())

        var entry: LetterModel
        entry = crossword.getLetterAt(5, 7)
        assertEquals('A', entry.letter)
        assertEquals(1, entry.words.count())
        assertEquals("BREAK", entry.words[0])
        assertEquals("What is it that I direly need? A ___?", entry.questions[0])
        assertEquals("3", entry.indices[0])
        assertEquals(1, entry.directions[0].x)
        assertEquals(0, entry.directions[0].y)

        entry = crossword.getLetterAt(2, 12)
        assertEquals('E', entry.letter)
        assertEquals(2, entry.words.count())
        assertEquals("TEST", entry.words[0])
        assertEquals("CURLY BRACKETS", entry.words[1])
        assertEquals("What is the purpose of this function? To ___?", entry.questions[0])
        assertEquals(
            "What are these swirly funny symbols surrounding this data entry?",
            entry.questions[1]
        )
        assertEquals("1", entry.indices[0])
        assertEquals("2", entry.indices[1])
        assertEquals(-1, entry.directions[0].x)
        assertEquals(0, entry.directions[0].y)
        assertEquals(0, entry.directions[1].x)
        assertEquals(1, entry.directions[1].y)

        entry = crossword.getLetterAt(2, 6)
        assertEquals(' ', entry.letter)
        assertEquals("CURLY BRACKETS", entry.words[0])
    }

    private val testData = """
    [{
        "id": "test_puzzle",
        "words": [
            {
                "i": "1",
                "x": 4,
                "y": 12,
                "d": "W",
                "q": "What is the purpose of this function? To ___?",
                "a": "TEST"
            },
            {
                "i": "2",
                "x": 2,
                "y": 1,
                "d": "S",
                "q": "What are these swirly funny symbols surrounding this data entry?",
                "a": "CURLY BRACKETS"
            },
            {
                "i": "3",
                "x": 2,
                "y": 7,
                "d": "E",
                "q": "What is it that I direly need? A ___?",
                "a": "BREAK"
            }
        ]
    }]
    """

}