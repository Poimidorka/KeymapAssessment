package org.exparser.parser

import org.exparser.expression.Expression
import org.exparser.expression.Operation

/**
 * Supporting class that helps to store operations and where they were occurred while parsing
 * @param operation
 */
class OperationEvent(val operation: Operation, val expression: Expression)