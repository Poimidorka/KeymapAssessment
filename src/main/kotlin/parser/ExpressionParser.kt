package org.exparser.parser

import org.exparser.expression.Expression

/**
 * dfs function that iterates through expression structure and forms an order
 * @param expression - Expression to dfs through
 * @param array - Array to store the order
 */
fun dfs(expression: Expression, array: ArrayList<String>) {
    val root = expression.queryTree.root
    when {
        root.isLeaf() -> {
            array.add("<step${array.size}> ::= ${root.leaf}")
        }
        root.isUnary() -> {
            dfs(expression.left()!!, array)
            array.add("<step${array.size}> ::= ${root.operation}<step${array.size - 1}>")
        }
        root.isBinary() -> {
            dfs(expression.left()!!, array)
            val leftNumber = array.size - 1
            dfs(expression.right()!!, array)
            val rightNumber = array.size - 1
            array.add("<step${array.size}> ::= (<step${leftNumber}>${root.operation}<step${rightNumber}>)")
        }
    }
}

/**
 * Disassembles the expression into the order of calculations
 * @param expression - a given expression
 * @return an array of strings (actions) to calculate
 */
fun makeEvaluationOrderFromExpression(expression: Expression) : ArrayList<String> {
    val result: ArrayList<String> = arrayListOf()
    dfs(expression, result)
    return result
}