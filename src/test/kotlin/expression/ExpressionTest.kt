package expression

import org.exparser.expression.Expression
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ExpressionTest {
    @Test
    fun testOperators() {
        var expression = Expression("1000")
        expression += expression
        expression -= expression
        expression *= expression
        assertEquals(expression.toString(), "(((1000+1000)-(1000+1000))*((1000+1000)-(1000+1000)))")
    }
    @Test
    fun testParserPart() {
        var expression = Expression("(((element+1000)*-112412414)--1000)")
        assertEquals(expression.toString(), "(((element+1000)*-112412414)--1000)")
    }
}