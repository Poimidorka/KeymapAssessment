package org.example.util

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
        fun checkCharacter(char: Char) : Boolean {
            return alphabet.contains(char)
        }
    }
    override fun toString(): String {
        return char.toString()
    }
}

/**
 * Represents the specific error for the wrong digit
 * @param errorMessage the error message
 */
class WrongCharacterError(private val errorMessage: String) : Error()
