package org.exparser.tree
import org.exparser.expression.Operation

/**
 * Supporting class for the Tree class
 * @property children the list of children
 * @property operation the operation in a node, might be null for leaves
 */
class Node(private var children: ArrayList<Node>) {
    var operation: Operation? = null
    var leaf: Leaf? = null
    constructor(operation: Operation, left: Node, right: Node) : this(arrayListOf<Node>(left, right)) {
        this.operation = operation
    }
    constructor(operation: Operation, argument: Node) : this(arrayListOf<Node>(argument)) {
        this.operation = operation
    }
    constructor(operation: Operation) : this(arrayListOf()) {
        this.operation = operation
    }
    constructor(string: String) : this(arrayListOf()) {
        this.leaf = Leaf(string)
    }
    /**
     * @returns true if the node is a leaf
     */
    fun isLeaf(): Boolean {
        return leaf != null
    }
    /**
     * @returns true if the node represents a binary operation
     */
    fun isBinary(): Boolean {
        return children.size == 2
    }
    /**
     * @returns true if the node represents an unary operation
     */
    fun isUnary(): Boolean {
        return children.size == 1
    }
    /**
     * @return the left children
     */
    fun left(): Node {
        return children[0]
    }
    /**
     * @return the right children
     */
    fun right(): Node {
        return children[children.size - 1]
    }

    /**
     * @return the number of children
     */
    fun childenCount(): Int {
        return children.size
    }
}