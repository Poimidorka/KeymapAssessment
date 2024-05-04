package org.example.tree

import org.example.expression.Operation

class QueryTree(var root: Node) {
    constructor(operation: Operation) : this(Node(operation))
    private fun toStringBuilder() : StringBuilder {
        val result = StringBuilder()
        when {
            root.isLeaf() -> result.append(root.leaf)
            root.isBinary() -> {
                val left = QueryTree(root.left()).toStringBuilder()
                val right = QueryTree(root.right()).toStringBuilder()
                result.append('(').append(left).append(')').
                append(root.operation).
                append('(').append(right).append(')')
            }
            root.isUnary() -> {
                result.append("Undefined-subexpression")
            }
            else -> {}
        }
        return result
    }
    override fun toString(): String {
        return toStringBuilder().toString()
    }
}