package kmm.example.app.shared.game.data

import kotlin.test.Test
import kotlin.test.assertEquals


class DataStoreUnitTest {

    @Test
    fun testDataStore() {
        val store = CrosswordDataStore()
        val crossword: Crossword = store.get()
        var entry: Letter

        assertEquals(3, crossword.wordCount)
        assertEquals(4 + 5 + 8 + 5, crossword.letterCount)
        entry = crossword.getLetterAt(5, 7)
        assertEquals("A", entry.letter)
        assertEquals(1, entry.words.length)
        assertEquals("BREAK", entry.words[0])
        assertEquals("What is it that I direly need? A ___?", entry.question[0])
        assertEquals(1, entry.directions[0].x)
        assertEquals(0, entry.directions[0].y)

        entry = crossword.getLetterAt(2, 12)
        assertEquals("E", entry.letter)
        assertEquals(2, entry.words.length)
        assertEquals("TEST", entry.word[0])
        assertEquals("CURLY BRACKETS", entry.word[1])
        assertEquals("What is the purpose of this function? To ___?", entry.questions[0])
        assertEquals(
            "What are these swirly funny symbols surrounding this data entry?",
            entry.questions[1]
        )
        assertEquals(-1, entry.directions[0].x)
        assertEquals(0, entry.directions[0].y)
        assertEquals(0, entry.directions[1].x)
        assertEquals(1, entry.directions[1].y)

        entry = crossword.getLetterAt(2, 6)
        assertEquals(" ", entry.letter)
        assertEquals("CURLY BRACKETS", entry.word[0])
    }

    val testData = """
    [
        {
            "n": 1,
            "x": 4,
            "y": 12,
            "d": "W",
            "q": "What is the purpose of this function? To ___?",
            "a": "TEST"
        },
        {
            "n": 2,
            "x": 2,
            "y": 1,
            "d": "S",
            "q": "What are these swirly funny symbols surrounding this data entry?",
            "a": "CURLY BRACKETS"
        },
        {
            "n": 3,
            "x": 2,
            "y": 7,
            "d": "E",
            "q": "What is it that I direly need? A ___?",
            "a": "BREAK"
        }
    ]
    """

}