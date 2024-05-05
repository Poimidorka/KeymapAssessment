# Arithmetic expression parser

[![Java CI with Gradle](https://github.com/Poimidorka/KeymapAssessment/actions/workflows/gradle.yml/badge.svg)](https://github.com/Poimidorka/KeymapAssessment/actions/workflows/gradle.yml)

The Kotlin project that consist of library that is able to parse the expressions
that satisfy the following grammar:

```pseudo
<digit> ::= “0” | “1" | “2” | “3" | “4” | “5" | “6” | “7" | “8” | “9"
<number> ::= <digit> | <digit> <number>
<operation> ::= “+” | “-” | “*”
<constant-expression> ::= “-” <number> | <number>
<binary-expression> ::= “(” <expression> <operation> <expression> “)”
<expression> ::= “element” | <constant-expression> | <binary-expression>
```

Using tree-like datastructures this project has linear time and memory complexity while parsing incoming data.

On top of parsing, this project provides a set of classes that represent expressions. 
These classes help to parse data and can be used as a tool to work with expressions: to create new expressions, 
to iterate over parts of expressions, etc.


## Table of Contents

- [Installation](#installation)
- [Code structure](#Structure)
- [Running Tests](#tests)
- [Usage](#usage)


## Installation

This project uses [Gradle](https://gradle.org) as the build system and [Groovy](https://groovy-lang.org) as a Gradle language.

To build the project, you can use the binary files at the root.
The following command will run a Gradle build task.

For Linux and macOS:
```bash
./gradlew build
```

For Windows:
```bash
bash gradlew.bat build
```

## Structure

- [src/main/kotlin](src/main/kotlin) - Source code root
  - [expression](src/main/kotlin/expression) - Package that contains classes for expressions and operations
    - [Expression.kt](src/main/kotlin/expression/Expression.kt) - Expression class and inheritances such as BinaryExpression and UnaryExpression
    - [Operation.kt](src/main/kotlin/expression/Operation.kt) - Operation enum class that is created for + - * binary operations
  - [parser](src/main/kotlin/parser) - Package that parsers incoming data and checks the validity of it
    - [ExpressionParser.kt](src/main/kotlin/parser/ExpressionParser.kt) - Dfs algorithm that makes an order of calculation by an Expression
    - [StringParser.kt](src/main/kotlin/parser/StringParser.kt) - Contains main function with stack algorithm to parse the data
    - [OperationEvent.kt](src/main/kotlin/parser/OperationEvent.kt) - Supportive data class to store operations as stack events 
    - [StringInputStream.kt](src/main/kotlin/parser/StringInputStream.kt) - Supportive class to combine InputStream and String together to make parsing process more convinient and succinct
  - [tree](src/main/kotlin/tree) - Package that represents QueryTree class, and it's supportive classes
    - [Leaf.kt](src/main/kotlin/tree/Leaf.kt) - Leaf data class that represents leaf data like "element" or Number
    - [Node.kt](src/main/kotlin/tree/Node.kt) - Node class for a tree that holds operation and list of nodes for this operation
    - [QueryTree.kt](src/main/kotlin/tree/QueryTree.kt) - Tree class that consists of nodes that can be used in classes like Expression to store data
  - [Main.kt](src/main/kotlin/Main.kt) - Provides a main function that makes an interactive process of parsing incoming data  
## Tests

Running all tests

In Linux and macOS:
```bash
./gradlew test
```

In Windows:
```bash
bash gradlew.bat test
```

## Usage

As a library:

```kotlin
import org.exparser.expression.Expression
fun main() {
    val a = Expression("(-999999999000+((1000-7)*-200))")
    val b = a + a - a * a
    println(b)
}
```
