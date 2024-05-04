package org.example.util

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
}

/**
 * Represents a single digit in a number.
 * @property char the character representing the digit
 */
data class Digit(private val char: Char) {
    init {
        if (!checkCharacter(char)) {
            throw WrongCharacterError("Adding '$char' into a Number class that is not in the alphabet")
        }
    }
    companion object {
        private val alphabet: HashSet<Char> = hashSetOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
        /**
         * Checks that the character is in the alphabet
         * @param char the character to check
         */
        public fun checkCharacter(char: Char) : Boolean {
            return alphabet.contains(char)
        }
    }
    fun toChar(): Char {
        return char
    }
}

/**
 * Represents the specific error for the wrong digit
 * @param errorMessage the error message
 */
class WrongCharacterError(private val errorMessage: String) : Error()
