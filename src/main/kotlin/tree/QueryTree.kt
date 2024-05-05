package org.exparser.tree

import org.exparser.expression.Operation
import org.exparser.util.StringLinkedBuilder

/**
 * Represents a tree-like data structure with Nodes
 * @property root the root of a tree
 */
class QueryTree(var root: Node) {
    constructor(operation: Operation) : this(Node(operation))

    /**
     * Recursively forms a StringBuilder
     * @return a StringBuilder for a tree
     */
    private fun toStringBuilder() : StringLinkedBuilder {
        val result = StringLinkedBuilder()
        when {
            root.isLeaf() -> result.append(root.leaf.toString())
            root.isBinary() -> {
                val left = QueryTree(root.left()).toStringBuilder()
                val right = QueryTree(root.right()).toStringBuilder()
                result.append('(').append(left).append(root.operation.toString()).append(right).append(')')
            }
            root.isUnary() -> {
                val left = QueryTree(root.left()).toStringBuilder()
                result.append(root.operation.toString()).append(left)
            }
            else -> {}
        }
        return result
    }
    override fun toString(): String {
        return toStringBuilder().toString()
    }
}