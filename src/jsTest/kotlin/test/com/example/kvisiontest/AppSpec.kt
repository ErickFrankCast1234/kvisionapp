package test.com.example.kvisiontest

import io.kvision.test.SimpleSpec
import kotlin.test.Test
import kotlin.test.assertTrue

class AppSpec : SimpleSpec {

    @Test
    fun render() {
        run {
            assertTrue(true, "Dummy test")
        }
    }
}
