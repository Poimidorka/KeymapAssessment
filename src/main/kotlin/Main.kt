package org.example
import java.util.Scanner

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        val scanner = Scanner(System.`in`)
        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()
        }
    }
}