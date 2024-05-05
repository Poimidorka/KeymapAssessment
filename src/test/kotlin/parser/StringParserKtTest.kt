package parser

import org.example.parser.makeExpressionFromString
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
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
}