package org.example.tree
import org.example.util.Number

data class Leaf(private val data: String) {
    private var isNumber = false
    private var isElement = false
    init {
        if (data == "element") {
            isElement = true
        }
        else if (Number.checkNumber(data)) {
            isNumber = true
        }
        else {
            throw WrongLeafException("Trying to make a leaf from a string \"$data\", which is " +
                    "not a Number or \"element\"")
        }
    }
    override fun toString(): String {
        return data
    }
}

class WrongLeafException(errorMessage: String) : Exception(errorMessage)