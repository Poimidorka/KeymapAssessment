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
    var result = ""
    while (!stream.isEmpty() && stream.currentChar().isLetter()) {
        result += stream.read()
    }
    if (result != "element") {
        throw ParserException("Words that are not equal to" +
                " \"element\" are not supported, $result != \"element\"")
    }
    return result
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
    val brackets : ArrayList<Bracket> = arrayListOf()
    val events: ArrayList<OperationEvent> = arrayListOf()
    var lastExpression: Expression? = null
    while (!stream.isEmpty()) {
        val currentChar = stream.currentChar()
        val index = stream.currentIndex()
        when {
            Operation.checkOperation(currentChar) -> {
                val operation = Operation.fromChar(stream.read())
                if (stream.isEmpty()) {
                    throw ParserException("String ends with the operation $operation")
                }
                if (index == 0 || stream[index - 1] != ')') {
                    if (operation != Operation.SUB) {
                        throw ParserException("Unsupported unary operation, $operation")
                    }
                    events.add(OperationEvent(operation, index,
                        UnaryExpression(operation.toString() + readNumber(stream))))
                    continue
                }
                if (lastExpression == null) {
                    throw ParserException("Left expression is not defined for the operation at position $index")
                }
                events.add(OperationEvent(operation, index, lastExpression))
            }
            currentChar == '(' -> {
                stream.read()
                brackets.add(Bracket(index, true))
            }
            currentChar.isLetter() -> {
                lastExpression = UnaryExpression(readWord(stream))
            }
            currentChar == ')' -> {
                val start = brackets.last()
                if (!start.isOpen) {
                    throw ParserException("Brackets error at position $index")
                }
                if (lastExpression == null) {
                    throw ParserException("")
                }
                lastExpression = applyOperation(events.last().expression, events.last().operation, lastExpression)
                brackets.removeLast()
                events.removeLast()
            }
            else -> {
                throw ParserException("Unsupported character $currentChar")
            }
        }
    }
    if (lastExpression == null || brackets.isNotEmpty()) {
        throw ParserException("Unsupported brackets or empty expression")
    }
    return lastExpression
}

class ParserException(errorMessage: String) : Exception(errorMessage)