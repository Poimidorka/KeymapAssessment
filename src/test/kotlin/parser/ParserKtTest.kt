package parser

import org.example.parser.makeExpression
import org.junit.jupiter.api.Test

class ParserKtTest {

    @Test
    fun smallTest() {
        val expressionToParse = "(1000-7)"
        makeExpression(expressionToParse)
    }
}