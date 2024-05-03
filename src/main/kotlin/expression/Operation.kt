package org.example.expression

/**
 * Enum that represents all operations that are allowed
 * @param symbol the symbol behind
 */
public enum class Operation(private val symbol: Char) {
    SUB('-'),
    ADD('+'),
    MUL('*');
    init {
        if (!checkOperation(symbol)) {
            throw WrongOperationError("Making operation from the unsupported character '$symbol'")
        }
    }
    companion object {
        private val operations: HashSet<Char> = hashSetOf('-', '+', '*')
        public fun checkOperation(char: Char) : Boolean {
            return operations.contains(char)
        }
    }
}

/**
 * Represents the specific error for the wrong type of operation
 * @param errorMessage the error message
 */
class WrongOperationError(private val errorMessage: String) : Error()
