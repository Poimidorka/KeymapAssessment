package org.example.parser

import org.example.expression.Expression
import org.example.expression.Operation

/**
 * Supporting class that helps to store operations and where they were occurred while parsing
 * @param operation
 */
class OperationEvent(val operation: Operation, val expression: Expression)