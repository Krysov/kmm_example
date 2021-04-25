package kmm.example.app.androidApp

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class IntegrationTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)
        scenario = launchActivity(intent)
    }

    @After
    fun cleanup() {
        scenario.close()
    }

    @Test
    fun initialTest() {
        scenario.onActivity { activity ->
            Assert.assertNotEquals(null, activity.findViewById(R.id.question_view))
            Assert.assertNotEquals(null, activity.findViewById(R.id.crossword_view))
        }
    }
}
