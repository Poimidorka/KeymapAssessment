package org.example.util

/**
 * Represents a number which has data as a String inside
 * Implementation supports a variety length of digits (more than int)
 * @property data the list that holds numbers for the char
 */
class Number(private val data: ArrayList<Digit>) {
    constructor(data: ArrayList<Char>) : this(arrayListOf<Digit>()) {
        for (element in data) {
           add(element)
        }
    }
    /**
     * Adds a new digit to the number.
     * @param char the character representing the digit to add
     * @throws Error if the character is not a valid digit
     */
    public fun add(char: Char) {
        data.add(Digit(char))
    }
}

/**
 * Represents a single digit in a number.
 * @property char the character representing the digit
 */
class Digit(private val char: Char) {
    init {
        if (!checkCharacter(char)) {
            throw WrongCharacterError("Adding '$char' into a Number class that is not in the alphabet")
        }
    }
    companion object {
        private val alphabet: HashSet<Char> = hashSetOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
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
