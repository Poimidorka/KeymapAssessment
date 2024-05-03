package org.example.tree
import org.example.expression.Operation

class Node(val chilren: ArrayList<Node>) {
    var operation: Operation? = null;
    private var leaf = null;
    public constructor(operation: Operation, left: Node, right: Node) : this(arrayListOf<Node>(left, right)) {
        this.operation = operation
    }
    public constructor(operation: Operation, argument: Node) : this(arrayListOf<Node>(argument)) {
        this.operation = operation
    }
    public fun isLeaf(): Boolean {
        return leaf != null
    }

}