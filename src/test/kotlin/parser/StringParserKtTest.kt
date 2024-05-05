package parser

import org.exparser.expression.Expression
import org.exparser.parser.ParserException
import org.exparser.parser.makeExpressionFromString
import org.exparser.util.StringLinkedBuilder
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis
import kotlin.random.Random

class StringParserKtTest {
    fun assertEqualExpression(expressionToParse: String) {
        assertEquals(expressionToParse, makeExpressionFromString(expressionToParse).toString())
    }
    @Test
    fun smallTest() {
        var expressionToParse = "(1000-7)"
        assertEqualExpression(expressionToParse)
        expressionToParse = "1000"
        assertEqualExpression(expressionToParse)
        expressionToParse = "-1000"
        assertEqualExpression(expressionToParse)
        expressionToParse = "(element-element)"
        assertEqualExpression(expressionToParse)
    }
    @Test
    fun iterativeSmallTest() {
        for (i in 1000 downTo 7 step 7) {
            val expressionToParse = "($i-7)"
            assertEqualExpression(expressionToParse)
        }
    }
    @Test
    fun randomTest() {
        val REPEATS = 50
        val ops = arrayListOf("-", "+", "*")
        repeat(REPEATS) {
            var expressionToParse = "(1000+1000)"
            repeat(3) {
                expressionToParse = "(" + expressionToParse + ops[Random.nextInt(0, 3)] + Random.nextInt() + ")"
            }
            assertEqualExpression(expressionToParse)
        }
    }
    @Test
    fun performanceTest() {
        // Might be modified for a specific machine
        val TIME_TRESHOLD = 2000
        var stringBuilder = StringLinkedBuilder("1000")
        for (i in 0..100000) {
            stringBuilder = StringLinkedBuilder("(").append(stringBuilder).append("+-123412)")
        }
        val expression = stringBuilder.toString()
        val timeInMillis = measureTimeMillis {
            makeExpressionFromString(expression)
        }
        assertTrue(timeInMillis < TIME_TRESHOLD)
    }
    @Test
    fun wrongSymbolTest() {
        val query = "(1000/1000)"
        var exceptionThrown = false
        try {
            makeExpressionFromString(query)
        } catch (exception: ParserException) {
            exceptionThrown = true
            assertEquals(exception.message, "Unsupported character /")
        }
        assertTrue(exceptionThrown)
    }
    @Test
    fun wrongBalanceTest() {
        val query = "((1000+1000)"
        var exceptionThrown = false
        try {
            makeExpressionFromString(query)
        } catch (exception: ParserException) {
            exceptionThrown = true
            assertEquals(exception.message, "Unsupported brackets or empty expression")
        }
        assertTrue(exceptionThrown)
    }
    @Test
    fun wrongEndingTest() {
        val query = "(1000+1000)+"
        var exceptionThrown = false
        try {
            makeExpressionFromString(query)
        } catch (exception: ParserException) {
            exceptionThrown = true
            assertEquals(exception.message, "String ends with the operation +")
        }
        assertTrue(exceptionThrown)
    }
}