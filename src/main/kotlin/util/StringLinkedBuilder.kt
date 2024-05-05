package org.exparser.util

/**
 * Supportive class that is an analog of StringBuilder but with LinkedList
 * Thus, builder.append(otherBuilder) will always be O(1) no matter how long otherBuilder is
 * @property head - the head of a LinkedList
 * @property tail - the tail of a LinkedList
 */
class StringLinkedBuilder(var head: StringLinkedListNode, var tail: StringLinkedListNode) {
    constructor() : this(StringLinkedListNode(""), StringLinkedListNode("")) {
        head = tail
    }
    constructor(string: String) : this(StringLinkedListNode(""), StringLinkedListNode("")) {
        val node = StringLinkedListNode(string)
        head = node
        tail = node
    }
    constructor(char: Char) : this(StringLinkedListNode(""), StringLinkedListNode("")) {
        val node = StringLinkedListNode(char.toString())
        head = node
        tail = node
    }
    companion object {
        /**
         * Make the StringLinkedList by adding them to each other
         */
        fun concatenate(left: StringLinkedBuilder, right: StringLinkedBuilder): StringLinkedBuilder {
            left.tail.next = right.head
            return StringLinkedBuilder(left.head, right.tail)
        }
    }
    /**
     * Iterate over nodes and make a string
     * @return string
     */
    override fun toString(): String {
        val stringBuilder = StringBuilder()
        var iter:StringLinkedListNode? = head
        while (iter != null && iter != tail) {
            stringBuilder.append(iter.data)
            iter = iter.next
        }
        if (iter == tail) {
            stringBuilder.append(iter.data)
        }
        return stringBuilder.toString()
    }
    /**
     * @param char - Char to add
     * @return this StringLinkedBuilder with appended values
     */
    fun append(char: Char): StringLinkedBuilder {
        val builder = concatenate(this, StringLinkedBuilder(char))
        tail = builder.tail
        head = builder.head
        return this
    }
    /**
     * @param string - String to add
     * @return this StringLinkedBuilder with appended values
     */
    fun append(string: String): StringLinkedBuilder {
        val builder = concatenate(this, StringLinkedBuilder(string))
        tail = builder.tail
        head = builder.head
        return this
    }
    /**
     * @param otherBuilder - other StringLinkedBuilder to concatenate
     * @return this StringLinkedBuilder with appended values
     */
    fun append(otherBuilder: StringLinkedBuilder): StringLinkedBuilder {
        val builder = concatenate(this, otherBuilder)
        tail = builder.tail
        head = builder.head
        return this
    }
}

/**
 * Supportive class for StringLinkedList
 * @property data - string that holds the data in a node
 */
class StringLinkedListNode(val data: String) {
    var next: StringLinkedListNode? = null
}