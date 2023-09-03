package com.example.spring_advanced_example.trace

import java.util.*

class TraceId(
    val id: String = createId(),
    val level: Int = 0
) {

    fun createNextId(): TraceId = TraceId(id, level + 1)

    fun createPreviousId(): TraceId = TraceId(id, level - 1)

    fun isFirstLevel(): Boolean = level == 0


    companion object{
        private fun createId(): String =
            UUID.randomUUID().toString().substring(0, 8)
    }
}


