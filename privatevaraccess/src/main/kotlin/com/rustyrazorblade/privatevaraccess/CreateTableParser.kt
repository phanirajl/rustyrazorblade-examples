package com.rustyrazorblade.privatevaraccess

import org.apache.cassandra.cql3.QueryProcessor
import org.apache.commons.lang3.reflect.FieldUtils

fun main(args: Array<String>) {
    println("Running the parser, yay")

    val query = """CREATE TABLE mytable (id int,
                        |cluster timeuuid,
                        |another_cluster timeuuid,
                        |val text,
                        |another map<int, int>,
                        |primary key(id, cluster, another_cluster))
                        |WITH CLUSTERING ORDER BY (cluster DESC, another_cluster ASC)""".trimMargin()

    println(query)

    val parsed = QueryProcessor.parseStatement(query)

    val f  =  FieldUtils.getField(parsed.javaClass, "definitions", true)
    val fields = FieldUtils.readField(f, parsed, true) as HashMap<*, *>
    for(f in fields) {

        println("Field: ${f.key}, Type: ${f.value}")
        val dataType = FieldUtils.getField(parsed.javaClass, "definitions", true)
    }

    val keyAliasesField = FieldUtils.getField(parsed.javaClass, "keyAliases", true)
    val keyAliases = FieldUtils.readField(keyAliasesField, parsed, true) as ArrayList<*>

    // primary keys
    println("Primary keys")
    for(k in keyAliases) {
        println("key: $k")
    }

    val columnAliasesField = FieldUtils.getField(parsed.javaClass, "columnAliases", true)
    val columnAliases = FieldUtils.readField(columnAliasesField, parsed, true) as ArrayList<*>

    println("Clustering keys")

    for(c in columnAliases) {
        println("$c.")
    }

}

