package com.example.spring_advanced_example.trace

class TraceStatus(
    val traceId: TraceId,
    val message: String,
    val startTimeMs: Long,
) {
}