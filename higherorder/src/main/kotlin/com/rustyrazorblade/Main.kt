package com.rustyrazorblade

class MarkdownTable() {
    fun row(function: () -> String) {
        println(function())
    }
}

fun table(meth: MarkdownTable.() -> Unit) : MarkdownTable {
    val t = MarkdownTable()
    t.meth()
    return t
}

fun main(args: Array<String>) {
    table {
        row {
            "baby"
        }
    }

}