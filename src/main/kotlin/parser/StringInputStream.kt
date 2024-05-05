package org.example.parser

/**
 * Represents a class that provides a char-like stream that is based on String
 * Helps to work with string while parsing data
 * @property string the buffer string
 */
class StringInputStream(private val string: String) {
    private var index = 0
    /**
     * Check that the index is at the end of buffer
     * @return the base string
     */
    fun isEmpty(): Boolean {
        return index == string.length
    }
    /**
     * Reads the data in the buffer and moves the index
     * @return the current char without reading
     */
    fun read(): Char {
        return string[index++]
    }
    /**
     * Peek functionality
     * @return the current char without reading or moving index
     */
    fun currentChar(): Char {
        return string[index]
    }
    /**
     * @return the current position of the index
     */
    fun currentIndex(): Int {
        return index
    }
    /**
     * Provides [] functionality to access single chars with an index
     * @return the char at the index position
     */
    operator fun get(index: Int): Char {
        return this.string[index]
    }
}