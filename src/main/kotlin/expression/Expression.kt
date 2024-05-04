package org.example.expression
import org.example.parser.makeExpression
import org.example.tree.Node
import org.example.tree.QueryTree

/**
 * Represents a base class for expressions
 * @param queryTree is a Tree class that represents the internal structure of an expression
 */
open class Expression(open val queryTree: QueryTree) {
    companion object {
        fun expressionFromString(string: String) : Expression {
            return makeExpression(string)
        }
    }
    operator fun plus(other: Expression) : BinaryExpression {
        return BinaryExpression(QueryTree(Node(Operation.ADD, queryTree.root, other.queryTree.root)))
    }
    operator fun minus(other: Expression) : BinaryExpression {
        return BinaryExpression(QueryTree(Node(Operation.SUB, queryTree.root, other.queryTree.root)))
    }
    operator fun times(other: Expression) : BinaryExpression {
        return BinaryExpression(QueryTree(Node(Operation.MUL, queryTree.root, other.queryTree.root)))
    }
}

class BinaryExpression(override val queryTree: QueryTree) : Expression(queryTree)

class UnaryExpression(override val queryTree: QueryTree) : Expression(queryTree) {
    constructor(string: String) : this(QueryTree(Operation.ADD)) {
        if (Operation.checkOperation(string[0])) {
            queryTree.root = Node(Operation.SUB, Node(string.substring(1)))
        } else {
            queryTree.root = Node(string)
        }
    }
}