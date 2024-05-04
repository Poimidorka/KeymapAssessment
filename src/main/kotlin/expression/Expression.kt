package org.example.expression
import org.example.parser.makeExpression
import org.example.tree.Node
import org.example.tree.Tree

/**
 * Represents a base class for expressions
 * @param tree is a Tree class that represents the internal structure of an expression
 */
open class Expression(open val tree: Tree) {
    companion object {
        fun expressionFromString(string: String) : Expression {
            return makeExpression(string)
        }
    }
    operator fun plus(other: Expression) : BinaryExpression {
        return BinaryExpression(Tree(Node(Operation.ADD, tree.root, other.tree.root)))
    }
    operator fun minus(other: Expression) : BinaryExpression {
        return BinaryExpression(Tree(Node(Operation.SUB, tree.root, other.tree.root)))
    }
    operator fun times(other: Expression) : BinaryExpression {
        return BinaryExpression(Tree(Node(Operation.MUL, tree.root, other.tree.root)))
    }
}

class BinaryExpression(override val tree: Tree) : Expression(tree)

class UnaryExpression(override val tree: Tree) : Expression(tree) {
    constructor(string: String) : this(Tree(Operation.ADD)) {
        if (Operation.checkOperation(string[0])) {
            tree.root = Node(Operation.SUB, Node(string.substring(1)))
        } else {
            tree.root = Node(string)
        }
    }
}