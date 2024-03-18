package org.example

import org.jetbrains.kotlin.js.parser.sourcemaps.JsonObject

class JsonBuilder {
    val json = JsonObject()

    fun array(name: String, build: JsonBuilder.() -> Unit) {
        val arrayBuilder = JsonBuilder()
        arrayBuilder.build()
        json[name] = arrayBuilder.json
    }

    fun obj(build: JsonBuilder.() -> Unit) {
        val objBuilder = JsonBuilder()
        objBuilder.build()
        json.put(objBuilder.json)
    }

    infix fun String.to(value: Any) {
        json[this] = value
    }
}

fun buildJson(build: JsonBuilder.() -> Unit): JsonObject {
    val builder = JsonBuilder()
    builder.build()
    return builder.json
}