package org.exparser.util

/**
 * Represents a number which has data as a String inside
 * Implementation supports a variety length of digits (more than int)
 * @property data the list that holds numbers for the char
 */
data class Number(private val data: ArrayList<Digit>) {
    constructor(data: String) : this(arrayListOf<Digit>()) {
        for (element in data) {
           add(element)
        }
    }
    constructor() : this(arrayListOf())
    /**
     * Adds a new digit to the number.
     * @param char the character representing the digit to add
     * @throws Error if the character is not a valid digit
     */
    fun add(char: Char) {
        data.add(Digit(char))
    }
    companion object {
        fun checkNumber(data: String): Boolean {
            for (element in data) {
                if (!Digit.checkCharacter(element)) {
                    return false
                }
            }
            return true
        }
    }

    override fun toString(): String {
        val builder = StringBuilder()
        for (digit in data) {
            builder.append(digit)
        }
        return builder.toString()
    }
}