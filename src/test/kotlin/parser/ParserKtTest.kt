package parser

import org.example.expression.Expression
import org.example.parser.makeExpression
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random

class ParserKtTest {

    @Test
    fun smallTest() {
        var expressionToParse = "(1000-7)"
        assertEquals(makeExpression(expressionToParse).toString(), expressionToParse)
        expressionToParse = "1000"
        assertEquals(makeExpression(expressionToParse).toString(), expressionToParse)
        expressionToParse = "-1000"
        assertEquals(makeExpression(expressionToParse).toString(), expressionToParse)
    }
    @Test
    fun iterativeSmallTest() {
        for (i in 1000 downTo 7 step 7) {
            val expressionToParse = "($i-7)"
            val result: Expression = makeExpression(expressionToParse)
            assertEquals(result.toString(), expressionToParse)
        }
    }
    @Test
    fun randomTest() {
        val REPEATS = 50
        repeat(REPEATS) {

        }
    }
}