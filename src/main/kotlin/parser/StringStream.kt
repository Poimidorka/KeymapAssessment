package org.example.parser

class StringStream(private val string: String) {
    private var index = 0
    fun isEmpty(): Boolean {
        return index < length()
    }
    fun read(): Char {
        return string[index++]
    }
    fun currentChar(): Char {
        return string[index]
    }
    fun currentIndex(): Int {
        return index
    }
    fun length(): Int {
        return string.length
    }
    operator fun get(index: Int): Char {
        return this.string[index]
    }
}