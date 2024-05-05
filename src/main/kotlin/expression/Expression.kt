package org.exparser.expression
import org.exparser.parser.makeExpressionFromString
import org.exparser.tree.Node
import org.exparser.tree.QueryTree

/**
 * Represents a base class for expressions
 * @param queryTree is a QueryTree class that represents the internal structure of an expression
 */
open class Expression(open var queryTree: QueryTree) {
    companion object {
        fun expressionFromString(string: String) : Expression {
            return makeExpressionFromString(string)
        }
    }
    constructor(string: String) : this(expressionFromString(string).queryTree)
    operator fun plus(other: Expression) : BinaryExpression {
        return BinaryExpression(QueryTree(Node(Operation.ADD, queryTree.root, other.queryTree.root)))
    }
    operator fun minus(other: Expression) : BinaryExpression {
        return BinaryExpression(QueryTree(Node(Operation.SUB, queryTree.root, other.queryTree.root)))
    }
    operator fun times(other: Expression) : BinaryExpression {
        return BinaryExpression(QueryTree(Node(Operation.MUL, queryTree.root, other.queryTree.root)))
    }

    override fun toString(): String {
        return queryTree.toString()
    }

    /**
     * @return left expression (if exists)
     */
    fun left() : Expression? {
        if (queryTree.root.childenCount() == 0)
            return null
        return Expression(QueryTree(queryTree.root.left()))
    }

    /**
     * @return right expression (if exists)
     */
    fun right() : Expression? {
        if (queryTree.root.childenCount() <= 1)
            return null
        return Expression(QueryTree(queryTree.root.right()))
    }
}

/**
 * Represents binary expressions like a - b
 */
class BinaryExpression(queryTree: QueryTree) : Expression(queryTree) {
    init {
        if (!queryTree.root.isBinary())
            throw WrongExpressionException("Attempting to create the Binary expression from a non-binary rooted tree")
    }
}

/**
 * Represents unary expressions like -a
 */
class UnaryExpression(queryTree: QueryTree) : Expression(queryTree) {
    constructor(string: String) : this(QueryTree(Operation.ADD)) {
        if (Operation.checkOperation(string[0]))
            queryTree.root = Node(Operation.SUB, Node(string.substring(1)))
        else
            queryTree.root = Node(string)
    }
}

class WrongExpressionException(errorMessage: String) : Exception(errorMessage)