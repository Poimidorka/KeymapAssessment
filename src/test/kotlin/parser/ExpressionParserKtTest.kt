package parser

import org.exparser.expression.Expression
import org.exparser.parser.makeEvaluationOrderFromExpression
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ExpressionParserKtTest {

    @Test
    fun testMakeEvaluationOrderFromExpression() {
        val expression = Expression("(((1000 - 1000) + -1000)*100)")
        val arrayToCompare = arrayListOf(
            "<step0> ::= 1000",
            "<step1> ::= 1000",
            "<step2> ::= (<step0>-<step1>)",
            "<step3> ::= 1000",
            "<step4> ::= -<step3>",
            "<step5> ::= (<step2>+<step4>)",
            "<step6> ::= 100",
            "<step7> ::= (<step5>*<step6>)"
            )
        assertEquals(makeEvaluationOrderFromExpression(expression), arrayToCompare)
    }
}