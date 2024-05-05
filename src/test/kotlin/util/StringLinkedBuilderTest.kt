package util

import org.exparser.util.StringLinkedBuilder
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class StringLinkedBuilderTest {

    @Test
    fun testBasic() {
        val builder = StringBuilder("aba")
        builder.append("caba")
        builder.append("daba")
        val builder2 = builder.append("caba")
        assertEquals("abacabadabacaba", builder.toString())
        assertEquals("abacabadabacaba", builder2.toString())
        assertTrue(builder == builder2)
    }

    @Test
    fun testComparison() {
        val builder = StringBuilder()
        val linkedBuilder = StringLinkedBuilder()
        for (i in 0..100000) {
            val letter: Char = 'a' + Random.nextInt(0, 26)
            builder.append(letter)
            linkedBuilder.append(letter)
        }
        assertEquals(builder.toString(), linkedBuilder.toString())
    }

    @Test
    fun testPerformance() {
        val TIME_TRESHOLD = 3000
        var builder = StringLinkedBuilder()
        val timeInMillis = measureTimeMillis {
            for (i in 0..100000) {
                builder = StringLinkedBuilder("a").append(builder)
            }
        }
        assertTrue(timeInMillis < TIME_TRESHOLD)
    }
}