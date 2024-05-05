package org.exparser
import org.exparser.parser.makeEvaluationOrderFromExpression
import org.exparser.parser.makeExpressionFromString
import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        for (string in makeEvaluationOrderFromExpression(makeExpressionFromString(line))) {
            println(string)
        }
    }
}