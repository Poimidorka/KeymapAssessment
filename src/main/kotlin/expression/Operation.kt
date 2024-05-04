package org.example.expression

/**
 * Enum that represents all operations that are allowed
 * @param symbol the symbol behind
 */
enum class Operation(private val symbol: Char) {
    SUB('-'),
    ADD('+'),
    MUL('*');
    init {
        if (!checkOperation(symbol)) {
            throw WrongOperationException("Making operation from the unsupported character '$symbol'")
        }
    }
    companion object {
        /**
         * @return true if the char represents a valid operation
         */
        fun checkOperation(char: Char) : Boolean {
            return enumValues<Operation>().find { it.symbol == char } != null
        }
        /**
         * @return the enum class that is based on a char
         */
        fun fromChar(char: Char): Operation {
            return enumValues<Operation>().find { it.symbol == char }!!
        }
    }
}

/**
 * Represents the specific error for the wrong type of operation
 * @param errorMessage the error message
 */
class WrongOperationException(private val errorMessage: String) : Exception()
