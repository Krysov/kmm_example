package kmm.example.app.shared.game.data

import kotlin.test.Test
import kotlin.test.assertEquals


class DataStoreUnitTest {

    @Test
    fun testDataModel() {
        val words = crossword.words
        println(words)
        println(words[0])
        assertEquals(4, words.count())
        assertEquals("1", words[0].index)
        assertEquals("2", words[1].index)
        assertEquals("3", words[2].index)
        println(crossword.letters)
        // sum of the length of all words minus the num of intersections
        assertEquals(4 + 14 + 5 + 5 - 3, crossword.letters.count())
    }

    @Test
    fun testLetterFetch() {
        val entry: LetterModel = crossword.getLetterAt(5, 7) as LetterModel
        assertEquals(Pair<Short, Short>(5, 7), entry.getLetterPosition())
        assertEquals('A', entry.letter)
        assertEquals(1, entry.words.count())
        assertEquals(3, entry.indices[0])
        assertEquals("BREAK", entry.words[0].answer)
        assertEquals("What is it that I direly need? A ___?", entry.words[0].question)
        assertEquals("3", entry.words[0].index)
        assertEquals(1, entry.words[0].direction.x)
        assertEquals(0, entry.words[0].direction.y)
    }

    @Test
    fun testIntersectingWords() {
        val entry: LetterModel = crossword.getLetterAt(2, 12) as LetterModel
        assertEquals(Pair<Short, Short>(2, 12), entry.getLetterPosition())
        assertEquals('E', entry.letter)
        assertEquals(2, entry.words.count())
        assertEquals(0, entry.getWordsLedByThis().count())
        assertEquals(1, entry.indices[0])
        assertEquals(11, entry.indices[1])
        assertEquals("TEST", entry.words[0].answer)
        assertEquals("CURLY BRACKETS", entry.words[1].answer)
        assertEquals("What is the purpose of this function? To ___?", entry.words[0].question)
        assertEquals(
            "What are these swirly funny symbols surrounding this data entry?",
            entry.words[1].question
        )
        assertEquals("1", entry.words[0].index)
        assertEquals("2", entry.words[1].index)
        assertEquals(-1, entry.words[0].direction.x)
        assertEquals(0, entry.words[0].direction.y)
        assertEquals(0, entry.words[1].direction.x)
        assertEquals(1, entry.words[1].direction.y)
    }

    @Test
    fun testBlankLetter() {
        val entry: LetterModel = crossword.getLetterAt(2, 6) as LetterModel
        assertEquals(Pair<Short, Short>(2, 6), entry.getLetterPosition())
        assertEquals(' ', entry.letter)
        assertEquals("CURLY BRACKETS", entry.words[0].answer)
    }

    @Test
    fun testEmptyFetch() {
        assertEquals(null, crossword.getLetterAt(3, 3))
        assertEquals(null, crossword.getLetterAt(3, 6))
        assertEquals(null, crossword.getLetterAt(6, 12))
        assertEquals(null, crossword.getLetterAt(-1, 0))
        assertEquals(null, crossword.getLetterAt(100, 0))
    }

    @Test
    fun testLedWords() {
        val entry: LetterModel = crossword.getLetterAt(2, 1) as LetterModel
        assertEquals(Pair<Short, Short>(2, 1), entry.getLetterPosition())
        assertEquals('C', entry.letter)
        assertEquals(listOf<Short>(0, 0), entry.indices)
        assertEquals(listOf("CURLY BRACKETS", "CLEAR"), entry.words.map { it.answer })
        assertEquals(2, entry.getWordsLedByThis().count())
    }

    private val testData = """
    [{
        "id": "test_puzzle",
        "words": [
            {
                "i": "1",
                "x": 3,
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
            },
            {
                "i": "4",
                "x": 2,
                "y": 1,
                "d": "E",
                "q": "The objective of this project is ___?",
                "a": "CLEAR"
            }
        ]
    }]
    """

    private val crossword = CrosswordDataStore(testData)
        .getForId("test_puzzle") as CrosswordModel

}