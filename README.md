# Arithmetic expression parser

The Kotlin project that consist of library that is able to parse the expressions
that satisfy the following grammar:

```pascal
<digit> ::= “0” | “1" | “2” | “3" | “4” | “5" | “6” | “7" | “8” | “9"
<number> ::= <digit> | <digit> <number>
<operation> ::= “+” | “-” | “*”
<constant-expression> ::= “-” <number> | <number>
<binary-expression> ::= “(” <expression> <operation> <expression> “)”
<expression> ::= “element” | <constant-expression> | <binary-expression>
```

Using tree-like datastructures this project has linear time and memory complexity while parsing incoming data.

## Table of Contents

- [Installation](#installation)
- [Code structure](#Structure)
- [Running Tests](#installation)
- [Usage](#usage)
- [Examples](#examples)


## Installation and building

This project uses Gradle as the build system.

To build the project, you can use the binary files at the root.
The following command will run a gradle build task.

For Linux and macOS:
```bash
./gradlew build
```

For Windows:
```bash
bash gradlew.bat build
```

The [build.gradle](build.gradle) file configures dependencies
and build process. 

E.g. it specifies jvmToolchain(17) for Kotlin, so it will use JDK 17 to build the project.

## Structure

## Tests

## Usage