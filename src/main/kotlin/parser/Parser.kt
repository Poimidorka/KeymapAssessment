package org.example.parser
import org.example.expression.Expression
import org.example.expression.Operation
import org.example.expression.UnaryExpression
import org.example.util.Digit
import org.example.util.Number

private fun readNumber(stream: StringStream): Number {
    val result = Number()
    while (!stream.isEmpty() && Digit.checkCharacter(stream.currentChar())) {
        result.add(stream.read())
    }
    if (result == Number()) {
        throw ParserException("Expecting a number, in an unary operation")
    }
    return result
}

private fun readWord(stream: StringStream): String {
    var word = ""
    while (!stream.isEmpty() && stream.currentChar().isLetter()) {
        word += stream.read()
    }
    if (word != "element") {
        throw ParserException("Only the word \"element\" is supported, found: $word")
    }
    return word
}

private fun applyOperation(left: Expression, operation: Operation, right: Expression): Expression {
    return when (operation) {
        Operation.ADD -> left + right
        Operation.SUB -> left - right
        Operation.MUL -> left * right
    }
}

fun makeExpression(data: String) : Expression {
    if (data.isEmpty()) {
        throw ParserException("Empty strings are not supported")
    }
    val stream = StringStream(data)
    var bracketsBalance = 0
    val events: ArrayList<OperationEvent> = arrayListOf()
    var lastExpression: Expression? = null
    //Reading all chars from a stream iteratively
    while (!stream.isEmpty()) {
        val currentChar = stream.currentChar()
        val index = stream.currentIndex()
        when {
            Operation.checkOperation(currentChar) -> {
                val operation = Operation.fromChar(stream.read())
                if (stream.isEmpty()) {
                    throw ParserException("String ends with the operation $operation")
                }
                if (index == 0 || stream[index - 1] == '(' || Operation.checkOperation(stream[index - 1])) {
                    if (operation != Operation.SUB) {
                        throw ParserException("Unsupported unary operation, $operation")
                    }
                    lastExpression = UnaryExpression(operation.toString() + readNumber(stream))
                    continue
                }
                if (lastExpression == null) {
                    throw ParserException("Left expression is not defined for the operation at position $index")
                }
                events.add(OperationEvent(operation, lastExpression))
            }
            currentChar.isLetter() -> {
                lastExpression = UnaryExpression(readWord(stream))
            }
            Digit.checkCharacter(currentChar) -> {
                lastExpression = UnaryExpression(readNumber(stream).toString())
            }
            currentChar == '(' -> {
                stream.read()
                bracketsBalance++
            }
            currentChar == ')' -> {
                stream.read()
                if (bracketsBalance == 0) {
                    throw ParserException("Brackets balance is negative at position $index")
                }
                if (lastExpression == null) {
                    throw ParserException("")
                }
                lastExpression = applyOperation(events.last().expression, events.last().operation, lastExpression)
                bracketsBalance--
                events.removeLast()
            }
            else -> {
                throw ParserException("Unsupported character $currentChar")
            }
        }
    }
    if (lastExpression == null || bracketsBalance != 0) {
        throw ParserException("Unsupported brackets or empty expression")
    }
    return lastExpression
}

class ParserException(errorMessage: String) : Exception(errorMessage)